package be.manager.remote;

import be.exceptions.BusinessException;
import org.springframework.stereotype.Component;

@Component
public interface SediuManagerRemote {

    String getDimensions() throws BusinessException;

    void updateDimensions(String dimensions) throws BusinessException;

}
