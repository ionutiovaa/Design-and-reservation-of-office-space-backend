package be.controller;

import be.dto.LocDTO;
import be.dto.UtilizareDTO;
import be.exceptions.BusinessException;
import be.manager.remote.UtilizareManagerRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/utilizari")
public class UtilizareController {

    @Autowired
    public UtilizareManagerRemote utilizareManagerRemote;

    public UtilizareController(UtilizareManagerRemote utilizareManagerRemote){
        this.utilizareManagerRemote = utilizareManagerRemote;
    }

    @PostMapping(path = "/createUtilizare", produces = "application/json")
    public ResponseEntity<?> saveUtilizare(@RequestBody UtilizareDTO utilizareDTO){
        try{
            LocDTO loc = utilizareManagerRemote.insertUtilizare(utilizareDTO);
            if (loc == null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This schedule already exists.");
            if (loc.getID() != null)
                return ResponseEntity.ok(loc);
            else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (BusinessException e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

}
