package be.controller;

import be.exceptions.BusinessException;
import be.manager.remote.SediuManagerRemote;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(path = "/sedii")
public class SediuController {

    @Autowired
    public SediuManagerRemote sediuManagerRemote;

    public SediuController(SediuManagerRemote sediuManagerRemote) {
        this.sediuManagerRemote = sediuManagerRemote;
    }

    @GetMapping(path = "/getDimensions", produces = "application/json")
    public String getDimensions() throws IOException, BusinessException {
        String dimensions = this.sediuManagerRemote.getDimensions();
        ObjectMapper jsonTransformer = new ObjectMapper();
        return jsonTransformer.writeValueAsString(dimensions);
    }

    @PostMapping(path = "/updateDimensions", produces = "application/json")
    public ResponseEntity<?> updateDimensions(@RequestParam String dimensions){
        try{
            sediuManagerRemote.updateDimensions(dimensions);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (BusinessException e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
