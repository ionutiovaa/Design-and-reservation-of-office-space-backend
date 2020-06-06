package be.controller;

import be.dto.LocDTO;
import be.exceptions.BusinessException;
import be.manager.remote.LocManagerRemote;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/locuri")
public class LocController {

    @Autowired
    public LocManagerRemote locManagerRemote;

    public LocController(LocManagerRemote locManagerRemote){
        this.locManagerRemote = locManagerRemote;
    }

    @GetMapping(path = "/getLocuri", produces = "application/json")
    public String getLocuri() throws IOException {
        List<LocDTO> listOfAllLocuri = this.locManagerRemote.findAllLocuri();
        ObjectMapper jsonTransformer = new ObjectMapper();
        return jsonTransformer.writeValueAsString(listOfAllLocuri);
    }

    @GetMapping(path = "/getLocuriByEtaj", produces = "application/json")
    public String getLocuri(@RequestParam Integer etaj) throws IOException {
        List<LocDTO> listOfAllLocuri = this.locManagerRemote.findAllLocuriByEtaj(etaj);
        ObjectMapper jsonTransformer = new ObjectMapper();
        return jsonTransformer.writeValueAsString(listOfAllLocuri);
    }

    @PostMapping(path = "/createLoc", produces = "application/json")
    public ResponseEntity<?> saveLoc(@RequestBody LocDTO locDTO){
        try{
            LocDTO loc = locManagerRemote.insertLoc(locDTO);
            if (loc == null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This place already exists.");
            if (loc.getID() != null)
                return ResponseEntity.ok(loc);
            else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (BusinessException e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @DeleteMapping(path = "/deleteLoc", produces = "application/json")
    public ResponseEntity<?> deleteLoc(@RequestParam String position) {
        try{
            locManagerRemote.deleteLocByPosition(position);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }


}
