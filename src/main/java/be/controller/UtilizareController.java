package be.controller;

import be.dto.*;
import be.exceptions.BusinessException;
import be.manager.remote.UtilizareManagerRemote;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping(path = "/utilizari")
public class UtilizareController {

    @Autowired
    public UtilizareManagerRemote utilizareManagerRemote;

    public UtilizareController(UtilizareManagerRemote utilizareManagerRemote){
        this.utilizareManagerRemote = utilizareManagerRemote;
    }

    @PostMapping(path = "/createUtilizare", produces = "application/json")
    public  ResponseEntity<?> saveUtilizare(@RequestBody UtilizareDTO utilizareDTO){
        try{
            String utilizare = utilizareManagerRemote.insertUtilizare(utilizareDTO);
            if (utilizare.equals(""))
                return new ResponseEntity<>(
                        "Not found",
                        HttpStatus.NOT_FOUND);
            if (utilizare.equals("This schedule exists already"))
                return new ResponseEntity<>(
                        "This schedule exists already.",
                        HttpStatus.BAD_REQUEST);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (BusinessException | ParseException e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping(path = "/getUtilizariByDate", produces = "application/json")
    public String getUtilizariByDateAndDesk(@RequestBody ForGetSchedulesDTO forGetSchedulesDTO) throws IOException, BusinessException {
        List<SchedulesDTO> listOfAllSchedules = this.utilizareManagerRemote.findAllSchedules(forGetSchedulesDTO);
        ObjectMapper jsonTransformer = new ObjectMapper();
        return jsonTransformer.writeValueAsString(listOfAllSchedules);
    }

    @PostMapping(path = "/checkFree", produces = "application/json")
    public ResponseEntity<Boolean> getUserType(@RequestBody UtilizareDTO utilizareDTO) throws BusinessException, ParseException {
        return new ResponseEntity<Boolean>(utilizareManagerRemote.checkFree(utilizareDTO), HttpStatus.OK);
    }

}
