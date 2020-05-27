/*
package be.controller;

import be.dto.DeskDTO;
import be.exceptions.BusinessException;
import be.manager.remote.DeskManagerRemote;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/desks")
public class DeskController {

    @Autowired
    public DeskManagerRemote deskManagerRemote;

    public DeskController(DeskManagerRemote deskManagerRemote){
        this.deskManagerRemote = deskManagerRemote;
    }

    @GetMapping(path = "/getDesks", produces = "application/json")
    public String getDesks() throws IOException {
        List<DeskDTO> listOfAllDesks = this.deskManagerRemote.findAllDesks();
        ObjectMapper jsonTransformer = new ObjectMapper();
        return jsonTransformer.writeValueAsString(listOfAllDesks);
    }

    @PostMapping(path = "/createDesk", produces = "application/json")
    public ResponseEntity<?> saveDesk(@RequestBody DeskDTO deskDTO){
        try{
            DeskDTO desk = deskManagerRemote.insertDesk(deskDTO);
            if (desk == null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This desk already exists.");
            if (desk.getID() != null)
                return ResponseEntity.ok(desk);
            else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (BusinessException e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @DeleteMapping(path = "/deleteDesk", produces = "application/json")
    public ResponseEntity<?> deleteDesk(@RequestParam Integer id) {
        try{
            deskManagerRemote.deleteDeskById(id);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

}
*/
