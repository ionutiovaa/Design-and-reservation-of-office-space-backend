package be.dtoEntityMappers;

import be.dto.DepartamentDTO;
import be.entity.Departament;

import java.util.ArrayList;
import java.util.List;

public class DepartamentDTOEntityMapper {

    private DepartamentDTOEntityMapper() {
    }

    public static Departament getDepartamentFromDepartamentDTO(DepartamentDTO departamentDTO){
        Departament departament = new Departament();
        if (departamentDTO != null){
            departament.setID(departamentDTO.getID());
            departament.setNume(departamentDTO.getNume());
            departament.setUser(UserDTOEntityMapper.getUserFromUserDTO(departamentDTO.getUser_responsabil()));
        }
        return departament;
    }

    public static DepartamentDTO getDTOFromDepartament(Departament departament){
        DepartamentDTO departamentDTO = new DepartamentDTO();
        if (departament != null){
            departamentDTO.setID(departament.getID());
            departamentDTO.setNume(departament.getNume());
            departamentDTO.setUser_responsabil(UserDTOEntityMapper.getDTOFromUser(departament.getUser()));
        }
        return departamentDTO;
    }

    public static List<DepartamentDTO> getAllClients(List<Departament> departaments){
        List<DepartamentDTO> clientDTOList = new ArrayList<>();
        departaments.forEach(departament -> clientDTOList.add(getDTOFromDepartament(departament)));
        return clientDTOList;
    }

}
