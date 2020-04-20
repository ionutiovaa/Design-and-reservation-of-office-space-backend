package be.manager.remote;

import be.dto.TokenDTO;
import org.springframework.stereotype.Component;

@Component
public interface TokenManagerRemote {

    TokenDTO findTokenByToken(String token);



}
