/*
package be.jwt.service;

import be.dao.TokenDao;
import be.dao.UserDao;
import be.dto.TokenDTO;
import be.dtoEntityMappers.TokenDTOEntityMapper;
import be.entity.Token;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TokenService {

    @Autowired
    UserDao userDao;

    @Autowired
    TokenDao tokenDao;

    public String login(TokenDTO tokenDTO){
        String username = tokenDTO.getUsername();
        //Optional tokenFound = tokenDao.login(username);
        String password = tokenDTO.getPassword();
        be.entity.User user = userDao.findUserByUsernameAndPassword(username, password);

        if (user != null*/
/* && tokenFound.isPresent()*//*
){
            Token tokenFound = tokenDao.login(username);

            
            String token = UUID.randomUUID().toString();
            Token tokenFounded = TokenDTOEntityMapper.getTokenFromTokenDTO(tokenDTO);
            Token tokenToSave = tokenDao.findByUsername(tokenFounded.getUsername());
            tokenToSave.setToken(token);

            tokenDao.update(tokenToSave.getID(), tokenToSave.getUsername(), tokenToSave.getToken());
            return token;
        }
        return StringUtils.EMPTY;
    }

    public Optional findByToken(String token){
        //Optional tokenFound = tokenDao.findByToken(token);
        Token tokenFound = tokenDao.findByToken(token);
        //if (tokenFound.isPresent()){
        if (tokenFound != null){
            be.entity.User user = userDao.findUserByUsername(tokenFound.getUsername());
            User userSecurity = new User(user.getUsername(), user.getPassword(), true, true, true, true, AuthorityUtils.createAuthorityList("USER"));
            return Optional.of(userSecurity);

            //Token tokenCasted = (Token) tokenFound.get();
            //be.entity.User user = userDao.findUserByUsername(tokenCasted.getUsername());
            //User userSecurity = new User(user.getUsername(), user.getPassword(), true, true, true, true, AuthorityUtils.createAuthorityList("USER"));
            //return Optional.of(user);
        }
        return Optional.empty();
    }

    public Token findById(Integer id){
        return tokenDao.findByID(id);
    }
}
*/
