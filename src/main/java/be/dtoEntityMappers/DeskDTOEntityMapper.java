/*
package be.dtoEntityMappers;

import be.dto.DeskDTO;
import be.entity.Desk;

import java.util.ArrayList;
import java.util.List;

public class DeskDTOEntityMapper {

    private DeskDTOEntityMapper(){
    }

    public static Desk getDeskFromDeskDTO(DeskDTO deskDTO){
        Desk desk = new Desk();
        if (deskDTO != null){
            desk.setID(deskDTO.getID());
            desk.setPozitie(deskDTO.getPozitie());
            desk.setQrCode(deskDTO.getQrCode());
            desk.setValue(deskDTO.getValue());
        }
        return desk;
    }

    public static DeskDTO getDTOFromDesk(Desk desk){
        DeskDTO deskDTO = new DeskDTO();
        if (desk != null){
            deskDTO.setID(desk.getID());
            deskDTO.setPozitie(desk.getPozitie());
            deskDTO.setQrCode(desk.getQrCode());
            deskDTO.setValue(desk.getValue());
        }
        return deskDTO;
    }

    public static List<DeskDTO> getAllDesks(List<Desk> desks){
        List<DeskDTO> deskDTOList = new ArrayList<>();
        desks.forEach(desk -> deskDTOList.add(getDTOFromDesk(desk)));
        return deskDTOList;
    }

}
*/
