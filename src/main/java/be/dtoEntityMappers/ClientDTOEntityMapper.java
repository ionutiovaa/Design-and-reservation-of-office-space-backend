package be.dtoEntityMappers;

import be.dto.ClientDTO;
import be.entity.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientDTOEntityMapper {

    private ClientDTOEntityMapper() {
    }

    public static Client getClientFromClientDTO(ClientDTO clientDTO){
        Client client = new Client();
        if (clientDTO != null){
            client.setID(clientDTO.getID());
            client.setNume(clientDTO.getNume());
        }
        return client;
    }

    public static ClientDTO getDTOFromClient(Client client){
        ClientDTO clientDTO = new ClientDTO();
        if (client != null){
            clientDTO.setID(client.getID());
            clientDTO.setNume(client.getNume());
        }
        return clientDTO;
    }

    public static ClientDTO getDTOAfterUpdateNume(Client client){
        ClientDTO clientDTO = getDTOFromClient(client);
        if (clientDTO != null)
            clientDTO.setNume(client.getNume());
        return clientDTO;
    }

    public static List<ClientDTO> getAllClients(List<Client> clients){
        List<ClientDTO> clientDTOList = new ArrayList<>();
        clients.forEach(client -> clientDTOList.add(getDTOFromClient(client)));
        return clientDTOList;
    }

}
