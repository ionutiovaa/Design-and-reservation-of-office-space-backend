/*
package be.dtoEntityMappers;

import be.dto.QRCodeDTO;
import be.entity.QRCode;

import java.util.ArrayList;
import java.util.List;

public class QRCodeDTOEntityMapper {

    private QRCodeDTOEntityMapper(){
    }

    public static QRCode getQRCodeFromQRCodeDTO(QRCodeDTO qrCodeDTO){
        QRCode qrCode = new QRCode();
        if (qrCodeDTO != null){
            qrCode.setID(qrCodeDTO.getID());
            qrCode.setNume(qrCodeDTO.getNume());
        }
        return qrCode;
    }

    public static QRCodeDTO getDTOFromQRCode(QRCode qrCode){
        QRCodeDTO qrCodeDTO = new QRCodeDTO();
        if (qrCode != null){
            qrCodeDTO.setID(qrCode.getID());
            qrCodeDTO.setNume(qrCode.getNume());
        }
        return qrCodeDTO;
    }

    public static QRCodeDTO getDTOAfterUpdateNume(QRCode qrCode){
        QRCodeDTO qrCodeDTO = getDTOFromQRCode(qrCode);
        if (qrCodeDTO != null)
            qrCodeDTO.setNume(qrCode.getNume());
        return qrCodeDTO;
    }

    public static List<QRCodeDTO> getAllQRCodes(List<QRCode> qrCodes){
        List<QRCodeDTO> qrCodeDTOList = new ArrayList<>();
        qrCodes.forEach(qrCode -> qrCodeDTOList.add(getDTOFromQRCode(qrCode)));
        return qrCodeDTOList;
    }

}
*/
