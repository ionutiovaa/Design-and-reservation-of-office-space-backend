package be.dtoEntityMappers;

import be.dto.UtilizareDTO;
import be.entity.Utilizare;

public class UtilizareDTOEntityMapper {

    private UtilizareDTOEntityMapper(){
    }

    public static UtilizareDTO getDTOFromUtilizare(Utilizare utilizare){
        UtilizareDTO utilizareDTO = new UtilizareDTO();
        if (utilizare != null){
            utilizareDTO.setID(utilizare.getID());
            utilizareDTO.setUsername(utilizare.getUser().getUsername());
            utilizareDTO.setStartDate(utilizare.getStartDate().toString());
            utilizareDTO.setFinalDate(utilizare.getFinalDate().toString());
        }
        return utilizareDTO;
    }

}
