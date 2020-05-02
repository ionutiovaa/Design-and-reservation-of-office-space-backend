package be.manager.impl;

import be.dao.ClientDao;
import be.dto.ChangeClientDTO;
import be.dto.ClientDTO;
import be.dtoEntityMappers.ClientDTOEntityMapper;
import be.entity.Client;
import be.exceptions.BusinessException;
import be.manager.remote.ClientManagerRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

@Component
public class ClientManager implements ClientManagerRemote {

    @Autowired
    private ClientDao clientDao;

    public ClientDao getClientDao() {
        return clientDao;
    }

    private Logger logger = Logger.getLogger(ClientManager.class.getName());

    @Override
    public ClientDTO insertClient(ClientDTO clientDTO) throws BusinessException {
        Client client = createClientToInsert(clientDTO);
        Client clientFinded = clientDao.findClientByNume(clientDTO.getNume());
        Client persisted = null;
        if (clientFinded == null){
            persisted = this.clientDao.save(client);
        }
        ClientDTO dtoPersisted = ClientDTOEntityMapper.getDTOFromClient(persisted);
        return dtoPersisted;
    }

    private Client createClientToInsert(ClientDTO clientDTO){
        Client client = new Client(clientDTO.getNume());
        return client;
    }

    @Override
    public List<ClientDTO> findAllClients() {
        List<Client> clients = clientDao.findAll();
        return ClientDTOEntityMapper.getAllClients(clients);
    }

    @Override
    public ClientDTO deleteClientByNume(String nume) throws Exception {
        Client client = clientDao.findClientByNume(nume);
        try{
            if (client != null){
                clientDao.delete(client);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return ClientDTOEntityMapper.getDTOFromClient(client);
    }

    @Override
    public ClientDTO findClientById(Integer id) throws BusinessException {
        Client client = clientDao.findClientByID(id);
        ClientDTO clientDTO = ClientDTOEntityMapper.getDTOFromClient(client);
        return clientDTO;
    }

    @Override
    public ClientDTO findClientByNume(String nume) throws BusinessException {
        Client client = clientDao.findClientByNume(nume);
        ClientDTO clientDTO = ClientDTOEntityMapper.getDTOFromClient(client);
        return clientDTO;
    }

    @Override
    public ClientDTO updateClient(ChangeClientDTO changeClientDTO) throws BusinessException {
        Client client = this.clientDao.findClientByNume(changeClientDTO.getOldNume());
        if (client == null)
            return null;
        if (client.getNume().equals(changeClientDTO.getOldNume())){
            client.setNume(changeClientDTO.getNewNume());
            int updated = this.clientDao.updateNumeClient(client.getID(), client.getNume());
            return ClientDTOEntityMapper.getDTOAfterUpdateNume(client);
        }
        else
            throw new BusinessException("Name error", "The old name is wrong");
    }
}
