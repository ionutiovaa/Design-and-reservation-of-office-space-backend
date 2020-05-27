package be.manager.remote;

import be.entity.Etaj;
import be.exceptions.BusinessException;
import org.springframework.stereotype.Component;

@Component
public interface EtajMangerRemote {

    Etaj insertEtaj() throws BusinessException;

    void deleteLastFloor() throws BusinessException;

    Integer getNumberOfFloorsBySediu(Integer sediu_id) throws BusinessException;

}
