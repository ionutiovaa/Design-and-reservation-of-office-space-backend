package be.controller;

import be.dto.TokenDTO;
import be.dto.UserDTO;
import be.entity.Token;
import be.jwt.model.JwtTokenUtil;
import be.jwt.req.JwtRequest;
import be.jwt.service.JwtService;
import be.manager.remote.UserManagerRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private UserManagerRemote userManagerRemote;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtService userDetailsService;


    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        JwtRequest jwtRequest = new JwtRequest();
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);
        Token t = userDetailsService.insertToken(new TokenDTO(token, userDetails.getUsername()));

        UserDTO loggedUser = userManagerRemote.findUserByUsernameAndPassword(authenticationRequest.getUsername(),
                authenticationRequest.getPassword());

        if(loggedUser != null) {
            //Disable password
            loggedUser.setPassword(null);
            //Set token
            loggedUser.setToken(t.getToken());
        }

        return ResponseEntity.ok(loggedUser);
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

}
