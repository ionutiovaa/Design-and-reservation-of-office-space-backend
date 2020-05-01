package be.manager.remote;

import be.dto.DepartamentDTO;
import be.exceptions.BusinessException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DepartamentManagerRemote {

    DepartamentDTO insertDepartament(DepartamentDTO departamentDTO) throws BusinessException;

    List<DepartamentDTO> findAllDepartaments();

    DepartamentDTO deleteDepartamentByNume(String nume) throws Exception;

    DepartamentDTO findDepartamentByNume(String nume) throws BusinessException;

}
