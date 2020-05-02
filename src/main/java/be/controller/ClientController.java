package be.controller;

import be.dto.ChangeClientDTO;
import be.dto.ClientDTO;
import be.exceptions.BusinessException;
import be.manager.remote.ClientManagerRemote;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
            if (client.getID() != null)
                return ResponseEntity.ok(client);
            else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This client already exists.");
        } catch (BusinessException e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    //O POT STERGE
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

    @DeleteMapping(path = "/deleteClient/{nume}", produces = "application/json")
    public ResponseEntity<?> deleteClient(@PathVariable String nume) {
        try{
            ClientDTO clientDTO = clientManagerRemote.deleteClientByNume(nume);
            if (clientDTO.getID() != null)
                return ResponseEntity.ok(HttpStatus.OK);
            else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping(path = "/updateClient", produces = "application/json")
    public ResponseEntity<?> updateClient(@RequestBody ChangeClientDTO changeClientDTO){
        try{
            ClientDTO updatedClient = clientManagerRemote.updateClient(changeClientDTO);
            if (updatedClient == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
