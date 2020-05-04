package be.manager.remote;

import be.dto.AddProiectToEchipaDTO;
import be.dto.ChangeNumeEchipaDTO;
import be.dto.EchipaDTO;
import be.dto.ProiectDTO;
import be.exceptions.BusinessException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EchipaManagerRemote {

    EchipaDTO insertEchipa(EchipaDTO echipaDTO) throws BusinessException;

    List<EchipaDTO> findAllEchipe();

    EchipaDTO deleteEchipaByNume(String nume) throws Exception;

    EchipaDTO findEchipaByNume(String nume) throws BusinessException;

    EchipaDTO changeNumeEchipa(ChangeNumeEchipaDTO changeNumeEchipaDTO) throws BusinessException;

    /*EchipaDTO addProiectToEchipa(AddProiectToEchipaDTO addProiectToEchipaDTO) throws BusinessException;*/

}
