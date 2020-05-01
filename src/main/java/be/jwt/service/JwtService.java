package be.jwt.service;

import be.dao.TokenDao;
import be.dao.UserDao;
import be.dto.TokenDTO;
import be.entity.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class JwtService implements UserDetailsService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private TokenDao tokenDao;

    public Token insertToken(TokenDTO tokenDTO) {
        return createTokenToInsert(tokenDTO);
    }

    private Token createTokenToInsert(TokenDTO tokenDTO) {
        Token token = new Token(tokenDTO.getToken(), tokenDTO.getUsername());
        return token;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        be.entity.User user = userDao.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        else {
            return new User(user.getUsername(),
                    new BCryptPasswordEncoder().encode(user.getPassword()),
                    new ArrayList<>());
        }
    }

}
