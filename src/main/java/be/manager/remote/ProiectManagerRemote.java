package be.manager.remote;

import be.dto.ChangeClientProiectDTO;
import be.dto.ChangeDepartamentProiectDTO;
import be.dto.ChangeNumeProiectDTO;
import be.dto.ProiectDTO;
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

}
