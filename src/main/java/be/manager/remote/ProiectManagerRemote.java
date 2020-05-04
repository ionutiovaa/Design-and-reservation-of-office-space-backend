package be.manager.remote;

import be.dto.*;
import be.exceptions.BusinessException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProiectManagerRemote {

    ProiectDTO insertProiect(ProiectDTO proiectDTO) throws BusinessException;

    List<ProiectDTO> findAllProiects();

    ProiectDTO deleteProiectByNume(String nume) throws Exception;

    ProiectDTO findProiectByNume(String nume) throws BusinessException;

    ProiectDTO changeNumeProiect(ChangeNumeProiectDTO changeNumeProiectDTO) throws BusinessException;

    ProiectDTO changeClientProiect(ChangeClientProiectDTO changeClientProiectDTO) throws BusinessException;

    ProiectDTO changeDepartamentProiect(ChangeDepartamentProiectDTO changeDepartamentProiectDTO) throws BusinessException;

    EchipaDTO addProiectToEchipa(AddProiectToEchipaDTO addProiectToEchipaDTO) throws BusinessException;
}
