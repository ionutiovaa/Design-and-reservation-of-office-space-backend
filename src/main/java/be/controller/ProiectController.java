package be.controller;

import be.dto.ChangeClientProiectDTO;
import be.dto.ChangeDepartamentProiectDTO;
import be.dto.ChangeNumeProiectDTO;
import be.dto.ProiectDTO;
import be.exceptions.BusinessException;
import be.manager.remote.ProiectManagerRemote;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/proiects")
public class ProiectController {

    @Autowired
    public ProiectManagerRemote proiectManagerRemote;

    public ProiectController(ProiectManagerRemote proiectManagerRemote){
        this.proiectManagerRemote = proiectManagerRemote;
    }

    @GetMapping(path = "/getProiects", produces = "application/json")
    public String getProiects() throws IOException {
        List<ProiectDTO> listOfAllProiects = this.proiectManagerRemote.findAllProiects();
        ObjectMapper jsonTransformer = new ObjectMapper();
        return jsonTransformer.writeValueAsString(listOfAllProiects);
    }

    @PostMapping(path = "/createProiect", produces = "application/json")
    public ResponseEntity<?> saveProiect(@RequestBody ProiectDTO proiectDTO){
        try{
            ProiectDTO proiect = proiectManagerRemote.insertProiect(proiectDTO);
            if (proiect == null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This proiect already exists.");
            if (proiect.getID() != null)
                return ResponseEntity.ok(proiect);
            else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (BusinessException e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @DeleteMapping(path = "/deleteProiect/{nume}", produces = "application/json")
    public ResponseEntity<?> deleteProiect(@PathVariable String nume) {
        try{
            ProiectDTO proiectDTO = proiectManagerRemote.deleteProiectByNume(nume);
            if (proiectDTO.getID() != null)
                return ResponseEntity.ok(HttpStatus.OK);
            else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping(path = "/updateProiect", produces = "application/json")
    public ResponseEntity<?> updateProiect(@RequestBody ChangeNumeProiectDTO changeNumeProiectDTO){
        try{
            ProiectDTO updatedProiect = proiectManagerRemote.changeNumeProiect(changeNumeProiectDTO);
            if (updatedProiect == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return ResponseEntity.ok(updatedProiect);
        } catch (BusinessException e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping(path = "/updateClientProiect", produces = "application/json")
    public ResponseEntity<?> updateClientProiect(@RequestBody ChangeClientProiectDTO changeClientProiectDTO){
        try{
            ProiectDTO updatedProiect = proiectManagerRemote.changeClientProiect(changeClientProiectDTO);
            if (updatedProiect != null)
                return ResponseEntity.ok(updatedProiect);
            else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (BusinessException e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping(path = "/updateDepartamentProiect", produces = "application/json")
    public ResponseEntity<?> updateDepartamentProiect(@RequestBody ChangeDepartamentProiectDTO changeDepartamentProiectDTO){
        try{
            ProiectDTO updatedProiect = proiectManagerRemote.changeDepartamentProiect(changeDepartamentProiectDTO);
            if (updatedProiect != null)
                return ResponseEntity.ok(updatedProiect);
            else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (BusinessException e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

}
