package be.controller;

import be.dto.DepartamentDTO;
import be.exceptions.BusinessException;
import be.manager.remote.DepartamentManagerRemote;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/departaments")
public class DepartamentController {

    @Autowired
    public DepartamentManagerRemote departamentManagerRemote;

    public DepartamentController(DepartamentManagerRemote departamentManagerRemote){
        this.departamentManagerRemote = departamentManagerRemote;
    }

    @GetMapping(path = "/getDepartaments", produces = "application/json")
    public String getDepartaments() throws IOException {
        List<DepartamentDTO> listOfAllDepartaments = this.departamentManagerRemote.findAllDepartaments();
        ObjectMapper jsonTransformer = new ObjectMapper();
        return jsonTransformer.writeValueAsString(listOfAllDepartaments);
    }

    @PostMapping(path = "/createDepartament", produces = "application/json")
    public ResponseEntity<?> saveDepartament(@RequestBody DepartamentDTO departamentDTO){
        try{
            DepartamentDTO departament = departamentManagerRemote.insertDepartament(departamentDTO);
            if (departament != null) {
                return ResponseEntity.ok(departament);
            }
            else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (BusinessException e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @DeleteMapping(path = "/deleteDepartament/{nume}", produces = "application/json")
    public ResponseEntity<?> deleteDepartament(@PathVariable String nume) {
        try{
            DepartamentDTO departamentDTO = departamentManagerRemote.deleteDepartamentByNume(nume);
            if (departamentDTO.getID() != null)
                return ResponseEntity.ok(HttpStatus.OK);
            else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

}
