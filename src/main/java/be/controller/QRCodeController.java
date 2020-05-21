/*
package be.controller;

import be.dto.QRCodeDTO;
import be.exceptions.BusinessException;
import be.manager.remote.QRCodeManagerRemote;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/qrcodes")
public class QRCodeController {

    @Autowired
    public QRCodeManagerRemote qrCodeManagerRemote;

    public QRCodeController(QRCodeManagerRemote qrCodeManagerRemote){
        this.qrCodeManagerRemote = qrCodeManagerRemote;
    }

    @GetMapping(path = "/getQRCodes", produces = "application/json")
    public String getQRCodes() throws IOException {
        List<QRCodeDTO> listOfAllQrcodes = this.qrCodeManagerRemote.findAllQRCodes();
        ObjectMapper jsonTransformer = new ObjectMapper();
        return jsonTransformer.writeValueAsString(listOfAllQrcodes);
    }

    @PostMapping(path = "/createQRCode", produces = "application/json")
    public ResponseEntity<?> saveQRCode(@RequestBody QRCodeDTO qrCodeDTO){
        try{
            QRCodeDTO qrCode = qrCodeManagerRemote.insertQRCode(qrCodeDTO);
            if (qrCode == null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This qrCode already exists.");
            if (qrCode.getID() != null)
                return ResponseEntity.ok(qrCode);
            else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (BusinessException e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @DeleteMapping(path = "/deleteQRCode", produces = "application/json")
    public ResponseEntity<?> deleteQRCode(@RequestParam String nume) {
        try{
            QRCodeDTO qrCodeDTO = qrCodeManagerRemote.deleteQRCodeByNume(nume);
            if (qrCodeDTO != null)
                return ResponseEntity.ok(HttpStatus.OK);
            else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

}
*/
