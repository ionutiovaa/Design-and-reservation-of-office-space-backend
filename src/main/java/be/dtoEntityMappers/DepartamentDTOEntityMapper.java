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

    public static List<DepartamentDTO> getAllDepartaments(List<Departament> departaments){
        List<DepartamentDTO> departamentDTOList = new ArrayList<>();
        departaments.forEach(departament -> departamentDTOList.add(getDTOFromDepartament(departament)));
        return departamentDTOList;
    }

    public static DepartamentDTO getDTOAfterUpdateNume(Departament departament){
        DepartamentDTO departamentDTO = getDTOFromDepartament(departament);
        if (departamentDTO != null)
            departamentDTO.setNume(departament.getNume());
        return departamentDTO;
    }

    public static DepartamentDTO getDTOAfterUpdateResponsabil(Departament departament){
        DepartamentDTO departamentDTO = getDTOFromDepartament(departament);
        if (departamentDTO != null)
            departamentDTO.setUser_responsabil(UserDTOEntityMapper.getDTOFromUser(departament.getUser()));
        return departamentDTO;
    }

}
