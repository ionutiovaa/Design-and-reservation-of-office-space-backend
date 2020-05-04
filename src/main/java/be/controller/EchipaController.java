package be.controller;

import be.dto.ChangeNumeEchipaDTO;
import be.dto.EchipaDTO;
import be.exceptions.BusinessException;
import be.manager.remote.EchipaManagerRemote;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/echipe")
public class EchipaController {

    @Autowired
    public EchipaManagerRemote echipaManagerRemote;

    public EchipaController(EchipaManagerRemote echipaManagerRemote){
        this.echipaManagerRemote = echipaManagerRemote;
    }

    @GetMapping(path = "/getEchipe", produces = "application/json")
    public String getEchipe() throws IOException {
        List<EchipaDTO> listOfAllEchipe = this.echipaManagerRemote.findAllEchipe();
        ObjectMapper jsonTransformer = new ObjectMapper();
        return jsonTransformer.writeValueAsString(listOfAllEchipe);
    }

    @PostMapping(path = "/createEchipa", produces = "application/json")
    public ResponseEntity<?> saveEchipa(@RequestBody EchipaDTO echipaDTO){
        try{
            EchipaDTO echipa = echipaManagerRemote.insertEchipa(echipaDTO);
            if (echipa == null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This team already exists.");
            if (echipa.getID() != null)
                return ResponseEntity.ok(echipa);
            else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (BusinessException e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @DeleteMapping(path = "/deleteEchipa/{nume}", produces = "application/json")
    public ResponseEntity<?> deleteEchipa(@PathVariable String nume) {
        try{
            EchipaDTO echipaDTO = echipaManagerRemote.deleteEchipaByNume(nume);
            if (echipaDTO.getID() != null)
                return ResponseEntity.ok(HttpStatus.OK);
            else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping(path = "/updateEchipa", produces = "application/json")
    public ResponseEntity<?> updateEchipa(@RequestBody ChangeNumeEchipaDTO changeNumeEchipaDTO){
        try{
            EchipaDTO updatedEchipa = echipaManagerRemote.changeNumeEchipa(changeNumeEchipaDTO);
            if (updatedEchipa == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return ResponseEntity.ok(updatedEchipa);
        } catch (BusinessException e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

}
