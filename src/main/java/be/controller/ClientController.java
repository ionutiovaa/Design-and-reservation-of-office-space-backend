package be.controller;

import be.dto.ChangeClientDTO;
import be.dto.ClientDTO;
import be.exceptions.BusinessException;
import be.manager.remote.ClientManagerRemote;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/clients")
public class ClientController {

    @Autowired
    public ClientManagerRemote clientManagerRemote;

    public ClientController(ClientManagerRemote clientManagerRemote){
        this.clientManagerRemote = clientManagerRemote;
    }

    @GetMapping(path = "/getClients", produces = "application/json")
    public String getClients() throws IOException {
        List<ClientDTO> listOfAllClients = this.clientManagerRemote.findAllClients();
        ObjectMapper jsonTransformer = new ObjectMapper();
        return jsonTransformer.writeValueAsString(listOfAllClients);
    }

    @PostMapping(path = "/createClient", produces = "application/json")
    public ResponseEntity<?> saveClient(@RequestBody ClientDTO clientDTO){
        try{
            ClientDTO client = clientManagerRemote.insertClient(clientDTO);
            return ResponseEntity.ok(client);
        } catch (BusinessException e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    //DE STERS
    @GetMapping(path = "/getClientById/{id}", produces = "application/json")
    public String getClientById(@PathVariable("id") Integer id){
        try{
            ClientDTO clientDTO = clientManagerRemote.findClientById(id);
            ObjectMapper jsonTransformer = new ObjectMapper();
            return jsonTransformer.writeValueAsString(clientDTO);
        } catch (BusinessException | JsonProcessingException e){
            return null;
        }
    }

    @GetMapping(path = "/getClient/{nume}", produces = "application/json")
    public String getClient(@PathVariable("nume") String nume){
        try{
            ClientDTO clientDTO = clientManagerRemote.findClientByNume(nume);
            ObjectMapper jsonTransformer = new ObjectMapper();
            return jsonTransformer.writeValueAsString(clientDTO);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping(path = "/deleteClient/{nume}", produces = "application/json")
    public Response deleteClient(@PathVariable String nume) {
        try{
            clientManagerRemote.deleteClientByNume(nume);
            return Response.status(Response.Status.OK).build();
        } catch (Exception e){
            e.printStackTrace();
            return Response.status(500).build();
        }
    }

    @PostMapping(path = "/updateClient", produces = "application/json")
    public ResponseEntity<?> updateClient(@RequestBody ChangeClientDTO changeClientDTO){
        try{
            ClientDTO updatedClient = clientManagerRemote.updateClient(changeClientDTO);
            return ResponseEntity.ok(updatedClient);
        } catch (BusinessException e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping(path="/", produces = "application/json")
    public String get()
    {
        return "HI";
    }



}
