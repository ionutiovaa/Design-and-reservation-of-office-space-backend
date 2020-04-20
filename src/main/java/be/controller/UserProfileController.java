package be.controller;

import be.entity.Token;
import be.jwt.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserProfileController {

    @Autowired
    private TokenService tokenService;

    @GetMapping(value = "/api/users/user/{id}",produces = "application/json")
    public Token getUserDetail(@PathVariable Integer id){
        return tokenService.findById(id);
    }

}
