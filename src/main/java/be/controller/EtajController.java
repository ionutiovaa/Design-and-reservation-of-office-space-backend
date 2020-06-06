package be.controller;

import be.entity.Etaj;
import be.exceptions.BusinessException;
import be.manager.remote.EtajMangerRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(path = "/etaje")
public class EtajController {

    @Autowired
    EtajMangerRemote etajMangerRemote;

    public EtajController(EtajMangerRemote etajMangerRemote) {
        this.etajMangerRemote = etajMangerRemote;
    }

    @PostMapping(path = "/createEtaj", produces = "application/json")
    public ResponseEntity<?> saveEtaj(){
        try{
            Etaj etaj = etajMangerRemote.insertEtaj();
            if (etaj.getID() != null)
                return ResponseEntity.ok(etaj);
            else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (BusinessException e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping(path = "/floorsCount", produces = "application/json")
    public Integer floorsCount() throws IOException, BusinessException {
        Integer countOfFloors = etajMangerRemote.getNumberOfFloorsBySediu(1);
        return countOfFloors;
    }

    @DeleteMapping(path = "/deleteEtaj", produces = "application/json")
    public ResponseEntity<?> deleteEtaj() {
        try{
            etajMangerRemote.deleteLastFloor();
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
