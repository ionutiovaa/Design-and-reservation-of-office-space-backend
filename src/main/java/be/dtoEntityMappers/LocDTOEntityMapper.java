package be.dtoEntityMappers;

import be.dto.LocDTO;
import be.entity.Loc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LocDTOEntityMapper {

    private LocDTOEntityMapper(){
    }

    public static Loc getLocFromLocDTO(LocDTO locDTO){
        Loc loc = new Loc();
        if (locDTO != null){
            loc.setID(locDTO.getID());
            loc.setQrCode(locDTO.getQrCode());
        }
        return loc;
    }

    public static LocDTO getDTOFromLoc(Loc loc){
        LocDTO locDTO = new LocDTO();
        if (loc != null){
            locDTO.setID(loc.getID());
            locDTO.setQrCode(loc.getQrCode());
        }
        return locDTO;
    }

    public static List<LocDTO> getAllLocuri(List<Loc> locuri){
        List<LocDTO> locDTOList = new ArrayList<>();
        locuri.forEach(loc -> locDTOList.add(getDTOFromLoc(loc)));
        return locDTOList;
    }

}
