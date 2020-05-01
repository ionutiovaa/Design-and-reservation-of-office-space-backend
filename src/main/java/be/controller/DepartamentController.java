package be.controller;

import be.dto.DepartamentDTO;
import be.exceptions.BusinessException;
import be.manager.remote.DepartamentManagerRemote;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
            return ResponseEntity.ok(departament);
        } catch (BusinessException e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping(path = "/deleteDepartament/{nume}", produces = "application/json")
    public Response deleteDepartament(@PathVariable String nume) {
        try{
            departamentManagerRemote.deleteDepartamentByNume(nume);
            return Response.status(Response.Status.OK).build();
        } catch (Exception e){
            e.printStackTrace();
            return Response.status(500).build();
        }
    }

}
