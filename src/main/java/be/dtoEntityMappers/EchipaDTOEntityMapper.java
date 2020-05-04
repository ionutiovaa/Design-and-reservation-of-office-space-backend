package be.dtoEntityMappers;

import be.dto.EchipaDTO;
import be.dto.ProiectDTO;
import be.entity.Echipa;
import be.entity.Proiect;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EchipaDTOEntityMapper {

    private EchipaDTOEntityMapper() {
    }

    public static Echipa getEhipaFromEchipaDTO(EchipaDTO echipaDTO){
        Echipa echipa = new Echipa();
        if (echipaDTO != null){
            echipa.setID(echipaDTO.getID());
            echipa.setNume(echipaDTO.getNume());
            echipa.setProiecte(ProiectDTOEntityMapper.getProiectSetFromDTO(echipaDTO.getProiecte()));
        }
        return echipa;
    }

    public static EchipaDTO getDTOFromEchipa(Echipa echipa){
        EchipaDTO echipaDTO = new EchipaDTO();
        if (echipa != null){
            echipaDTO.setID(echipa.getID());
            echipaDTO.setNume(echipa.getNume());
            echipaDTO.setProiecte(ProiectDTOEntityMapper.getProiectDTOSetFromProiect(echipa.getProiecte()));
        }
        return echipaDTO;
    }

    public static List<EchipaDTO> getAllEchipe(List<Echipa> echipe){
        List<EchipaDTO> echipaDTOList = new ArrayList<>();
        echipe.forEach(echipa -> echipaDTOList.add(getDTOFromEchipa(echipa)));
        return echipaDTOList;
    }

    public static EchipaDTO getDTOAfterUpdateNume(Echipa echipa){
        EchipaDTO echipaDTO = getDTOFromEchipa(echipa);
        if (echipaDTO != null)
            echipaDTO.setNume(echipa.getNume());
        return echipaDTO;
    }

    /*public static EchipaDTO getDTOAfterInsertProiect(Echipa echipa){
        EchipaDTO echipaDTO = getDTOFromEchipa(echipa);
        Set<ProiectDTO> proiectDTOSet = new HashSet<>();
        echipa.getProiecte().forEach(proiect -> proiectDTOSet.add(ProiectDTOEntityMapper.getDTOFromProiect(proiect)));
        if (echipaDTO != null)
            echipaDTO.setProiecte(proiectDTOSet);
        return echipaDTO;
    }*/

}
