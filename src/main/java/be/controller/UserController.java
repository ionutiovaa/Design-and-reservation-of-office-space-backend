package be.controller;

import be.dto.*;
import be.entity.Token;
import be.entity.types.UserType;
import be.exceptions.BusinessException;
import be.jwt.model.JwtTokenUtil;
import be.jwt.req.JwtRequest;
import be.jwt.service.JwtService;
import be.manager.remote.UserManagerRemote;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class UserController extends HttpServlet {

    @Autowired
    public UserManagerRemote userManagerRemote;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public UserController() {
    }

    public UserController(UserManagerRemote userManagerRemote){
        this.userManagerRemote = userManagerRemote;
    }

    @GetMapping(path = "/", produces = "application/json")
    public String get(){return "HI"; }

    @PostMapping(path = "/changePassword", produces = "application/json")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordDTO changePasswordDTO){
        try{
            UserDTO updateUser = userManagerRemote.changePassword(changePasswordDTO);
            return ResponseEntity.ok(updateUser);
        } catch (BusinessException e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping(path = "/getUsers", produces = "application/json")
    public String getUsers() throws IOException{
        List<UserDTO> listOfAllUsers = this.userManagerRemote.findAllUsers();
        ObjectMapper jsonTranformer = new ObjectMapper();
        return jsonTranformer.writeValueAsString(listOfAllUsers);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    /*@PostMapping(path = "/createUser", produces = "application/json")
    public ResponseEntity<?> addUser(@RequestBody UserDTO userDTO){
        try {
            UserDTO user = userManagerRemote.insertUser(userDTO);
            if (user == null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This user already exists.");
            return ResponseEntity.ok(user);
        } catch (BusinessException e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }*/

    @PostMapping(path = "/createUser", produces = "application/json")
    public ResponseEntity<?> addUser(@RequestBody UserDTO userDTO){
        try {
            UserDTO user = userManagerRemote.insertUser(userDTO);
            if (user == null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This user already exists.");
            UserDTO loggedUser = createAuthenticationToken(new JwtRequest(user.getUsername(), user.getPassword()));
            return ResponseEntity.ok(loggedUser);
        } catch (BusinessException e){
            return ResponseEntity.status(500).body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    public UserDTO createAuthenticationToken(JwtRequest authenticationRequest) throws Exception {
        JwtRequest jwtRequest = new JwtRequest();
        String encryptedPassword = Base64.getEncoder().encodeToString(authenticationRequest.getPassword().getBytes());
        //authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        authenticate(authenticationRequest.getUsername(), encryptedPassword);

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);
        Token t = userDetailsService.insertToken(new TokenDTO(token, userDetails.getUsername()));

        //UserDTO loggedUser = userManagerRemote.findUserByUsernameAndPassword(authenticationRequest.getUsername(),
        //       authenticationRequest.getPassword());
        UserDTO loggedUser = userManagerRemote.findUserByUsernameAndPassword(authenticationRequest.getUsername(),
                encryptedPassword);

        if(loggedUser != null) {
            //Disable password
            loggedUser.setPassword(null);
            //Set token
            loggedUser.setToken(t.getToken());
        }
        System.out.println(token);
        return loggedUser;
    }

    @DeleteMapping(path = "/deleteUser", produces = "application/json")
    public ResponseEntity<?> deleteUser(@RequestParam Integer id) {
        try{
            UserDTO userDTO = userManagerRemote.deleteUserById(id);
            if (userDTO != null)
                return ResponseEntity.ok(HttpStatus.OK);
            else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping(path = "/getUser/{id}", produces = "application/json")
    public String getUser(@PathVariable("id") Integer id){
        try {
            UserDTO userDTO = userManagerRemote.findUser(id);
            ObjectMapper jsonTransformer = new ObjectMapper();
            return jsonTransformer.writeValueAsString(userDTO);
        } catch (BusinessException | JsonProcessingException e){
            return null;
        }
    }

    @PostMapping(path = "/addUserToEchipa", produces = "application/json")
    public ResponseEntity<?> addUserToEchipa(@RequestBody AddUserToEchipaDTO addUserToEchipaDTO){
        try{
            EchipaDTO updatedEchipa = userManagerRemote.addUserToEchipa(addUserToEchipaDTO);
            if (updatedEchipa != null)
                return ResponseEntity.ok(updatedEchipa);
            else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (BusinessException e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping(path = "/getUserType", produces = "application/json")
    public ResponseEntity<UserType> getUserType(@RequestParam String username) throws BusinessException {
        return new ResponseEntity<>(userManagerRemote.getUserType(username), HttpStatus.OK);
    }

}
