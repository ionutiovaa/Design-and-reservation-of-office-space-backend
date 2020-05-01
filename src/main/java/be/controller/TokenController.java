/*
package be.controller;

import be.dto.TokenDTO;
import be.jwt.service.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/login2")
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @PostMapping(path = "/token")
    public ResponseEntity<TokenDTO> getToken(@RequestBody TokenDTO tokenDTO){
        String token = tokenService.login(tokenDTO);
        TokenDTO tokenToReturn = new TokenDTO();
        tokenToReturn.setUsername(tokenDTO.getUsername());
        tokenToReturn.setToken(token);
        if(StringUtils.isEmpty(token)){
            return ResponseEntity.status(401).body(new TokenDTO());
        }
        return ResponseEntity.accepted().body(tokenToReturn);
    }

    */
/*@PostMapping(path = "/token")
    public String getToken(@RequestBody TokenDTO tokenDTO){
        String token= tokenService.login(tokenDTO);
        if(StringUtils.isEmpty(token)){
            return "NotFound";
        }
        return token;
    }*//*

}
*/
