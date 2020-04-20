package be.dtoEntityMappers;

import be.dto.TokenDTO;
import be.entity.Token;

public class TokenDTOEntityMapper {

    private TokenDTOEntityMapper(){}

    public static TokenDTO getDTOFromToken(Token token){
        TokenDTO tokenDTO = new TokenDTO();
        if (token != null){
            tokenDTO.setID(token.getID());
            tokenDTO.setToken(token.getToken());
            tokenDTO.setUsername(token.getUsername());
        }
        return tokenDTO;
    }

    public static Token getTokenFromTokenDTO(TokenDTO tokenDTO){
        Token token = new Token();
        if (tokenDTO != null){
            token.setID(tokenDTO.getID());
            token.setToken(tokenDTO.getToken());
            token.setUsername(tokenDTO.getUsername());
        }
        return token;
    }

}
