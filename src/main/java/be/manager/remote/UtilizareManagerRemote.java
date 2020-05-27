package be.manager.remote;

import be.dto.LocDTO;
import be.dto.UtilizareDTO;
import be.exceptions.BusinessException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UtilizareManagerRemote {

    LocDTO insertUtilizare(UtilizareDTO utilizareDTO) throws BusinessException;

    //DeskDTO insertUtilizare(UtilizareDTO utilizareDTO) throws BusinessException;

    List<UtilizareDTO> findAllUtilizari();

    void deleteUtilizareById(Integer id) throws BusinessException;

}
