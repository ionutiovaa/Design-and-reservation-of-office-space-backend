package be.manager.remote;

import be.dto.ChangeClientDTO;
import be.dto.ClientDTO;
import be.exceptions.BusinessException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ClientManagerRemote {

    ClientDTO insertClient(ClientDTO clientDTO) throws BusinessException;

    List<ClientDTO> findAllClients();

    void deleteClientByNume(String nume) throws Exception;

    ClientDTO findClientById(Integer id) throws BusinessException;

    ClientDTO findClientByNume(String nume) throws BusinessException;

    ClientDTO updateClient(ChangeClientDTO changeClientDTO) throws BusinessException;

}
