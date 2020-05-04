package be.dtoEntityMappers;

import be.dto.ProiectDTO;
import be.entity.Proiect;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProiectDTOEntityMapper {

    private ProiectDTOEntityMapper(){
    }

    public static Proiect getProiectFromProiectDTO(ProiectDTO proiectDTO){
        Proiect proiect = new Proiect();
        if (proiectDTO != null){
            proiect.setID(proiectDTO.getID());
            proiect.setNume(proiectDTO.getNume());
            proiect.setClient(ClientDTOEntityMapper.getClientFromClientDTO(proiectDTO.getClient()));
            proiect.setDepartament(DepartamentDTOEntityMapper.getDepartamentFromDepartamentDTO(proiectDTO.getDepartament()));
        }
        return proiect;
    }

    public static ProiectDTO getDTOFromProiect(Proiect proiect){
        ProiectDTO proiectDTO = new ProiectDTO();
        if (proiect != null){
            proiectDTO.setID(proiect.getID());
            proiectDTO.setNume(proiect.getNume());
            proiectDTO.setClient(ClientDTOEntityMapper.getDTOFromClient(proiect.getClient()));
            proiectDTO.setDepartament(DepartamentDTOEntityMapper.getDTOFromDepartament(proiect.getDepartament()));
        }
        return proiectDTO;
    }

    public static List<ProiectDTO> getAllProiects(List<Proiect> proiects){
        List<ProiectDTO> proiectDTOList = new ArrayList<>();
        proiects.forEach(proiect -> proiectDTOList.add(getDTOFromProiect(proiect)));
        return proiectDTOList;
    }

    public static ProiectDTO getDTOAfterUpdateNume(Proiect proiect){
        ProiectDTO proiectDTO = getDTOFromProiect(proiect);
        if (proiectDTO != null)
            proiectDTO.setNume(proiect.getNume());
        return proiectDTO;
    }

    public static ProiectDTO getDTOAfterUpdateClient(Proiect proiect){
        ProiectDTO proiectDTO = getDTOFromProiect(proiect);
        if (proiectDTO != null)
            proiectDTO.setClient(ClientDTOEntityMapper.getDTOFromClient(proiect.getClient()));
        return proiectDTO;
    }

    public static ProiectDTO getDTOAfterUpdateDepartament(Proiect proiect){
        ProiectDTO proiectDTO = getDTOFromProiect(proiect);
        if (proiectDTO != null)
            proiectDTO.setDepartament(DepartamentDTOEntityMapper.getDTOFromDepartament(proiect.getDepartament()));
        return proiectDTO;
    }

    public static Set<Proiect> getProiectSetFromDTO(Set<ProiectDTO> proiectDTOSet){
        Set<Proiect> proiecte = new HashSet<>();
        proiectDTOSet.forEach(proiectDTO -> proiecte.add(getProiectFromProiectDTO(proiectDTO)));
        return proiecte;
    }

    public static Set<ProiectDTO> getProiectDTOSetFromProiect(Set<Proiect> proiectSet){
        Set<ProiectDTO> proiectDTOSet = new HashSet<>();
        proiectSet.forEach(proiect -> proiectDTOSet.add(getDTOFromProiect(proiect)));
        return proiectDTOSet;
    }

}
