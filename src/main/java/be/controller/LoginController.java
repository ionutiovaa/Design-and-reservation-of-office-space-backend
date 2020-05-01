package be.controller;

import be.dto.UserDTO;
import be.exceptions.BusinessException;
import be.manager.remote.UserManagerRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/login")
public class LoginController {

    @Autowired
    public UserManagerRemote userManagerRemote;

    @PostMapping(path="/l", produces = "application/json")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) throws BusinessException {
        UserDTO dtoResponse = userManagerRemote.findUserByUsernameAndPassword(userDTO.getUsername(), userDTO.getPassword());
        dtoResponse.setPassword(null);
        return ResponseEntity.ok(dtoResponse);
    }

}
