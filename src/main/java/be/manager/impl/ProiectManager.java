package be.manager.impl;

import be.dao.*;
import be.dto.*;
import be.dtoEntityMappers.EchipaDTOEntityMapper;
import be.dtoEntityMappers.ProiectDTOEntityMapper;
import be.entity.Client;
import be.entity.Departament;
import be.entity.Echipa;
import be.entity.Proiect;
import be.exceptions.BusinessException;
import be.manager.remote.ProiectManagerRemote;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@Component
public class ProiectManager implements ProiectManagerRemote {

    @Autowired
    private ProiectDao proiectDao;

    @Autowired
    private ClientDao clientDao;

    @Autowired
    private DepartamentDao departamentDao;

    @Autowired
    private EchipaDao echipaDao;


    private Logger logger = Logger.getLogger(ClientManager.class.getName());

    @Override
    public ProiectDTO insertProiect(ProiectDTO proiectDTO) throws BusinessException {
        Client client = clientDao.findClientByNume(proiectDTO.getClient().getNume());
        Departament departament = departamentDao.findDepartamentByNume(proiectDTO.getDepartament().getNume());
        Proiect proiect = proiectDao.findProiectByNume(proiectDTO.getNume());
        if (proiect != null)
            return null;
        ProiectDTO dtoPersisted = new ProiectDTO();
        if (client != null && departament != null){
            proiect = new Proiect();
            proiect.setNume(proiectDTO.getNume());
            proiect.setClient(client);
            proiect.setDepartament(departament);
            Proiect persistedProiect = proiectDao.save(proiect);
            dtoPersisted = ProiectDTOEntityMapper.getDTOFromProiect(persistedProiect);
        }
        return dtoPersisted;
    }

    @Override
    public List<ProiectDTO> findAllProiects() {
        List<Proiect> proiects = proiectDao.findAll();
        return ProiectDTOEntityMapper.getAllProiects(proiects);
    }

    @Override
    public ProiectDTO deleteProiectByNume(String nume) throws Exception {
        Proiect proiect = proiectDao.findProiectByNume(nume);
        try{
            if (proiect != null)
                proiectDao.delete(proiect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ProiectDTOEntityMapper.getDTOFromProiect(proiect);
    }

    @Override
    public ProiectDTO findProiectByNume(String nume) throws BusinessException {
        Proiect proiect = proiectDao.findProiectByNume(nume);
        ProiectDTO proiectDTO = ProiectDTOEntityMapper.getDTOFromProiect(proiect);
        return proiectDTO;
    }

    @Override
    public ProiectDTO changeNumeProiect(ChangeNumeProiectDTO changeNumeProiectDTO) throws BusinessException {
        Proiect proiect = proiectDao.findProiectByNume(changeNumeProiectDTO.getOldNume());
        if (proiect == null)
            return null;
        if (proiect.getNume().equals(changeNumeProiectDTO.getOldNume())){
            proiect.setNume(changeNumeProiectDTO.getNewNume());
            int updated = proiectDao.changeNumeProiect(proiect.getID(), proiect.getNume());
            return ProiectDTOEntityMapper.getDTOAfterUpdateNume(proiect);
        }
        else
            throw new BusinessException("Name error", "The old name is wrong");
    }

    @Override
    public ProiectDTO changeClientProiect(ChangeClientProiectDTO changeClientProiectDTO) throws BusinessException {
        Proiect proiect = proiectDao.findProiectByNume(changeClientProiectDTO.getNume());
        Client client = clientDao.findClientByNume(changeClientProiectDTO.getOldClient());
        Client newClient = clientDao.findClientByNume(changeClientProiectDTO.getNewClient());
        if (proiect != null && client != null && newClient != null){
            int updated = proiectDao.updateClientProiect(proiect.getID(), newClient.getID());
            proiect.setClient(newClient);
            return ProiectDTOEntityMapper.getDTOAfterUpdateClient(proiect);
        }
        else
            return null;
    }

    @Override
    public ProiectDTO changeDepartamentProiect(ChangeDepartamentProiectDTO changeDepartamentProiectDTO) throws BusinessException {
        Proiect proiect = proiectDao.findProiectByNume(changeDepartamentProiectDTO.getNume());
        Departament departament = departamentDao.findDepartamentByNume(changeDepartamentProiectDTO.getOldDepartament());
        Departament newDepartament = departamentDao.findDepartamentByNume(changeDepartamentProiectDTO.getNewDepartament());
        if (proiect != null && departament != null && newDepartament != null){
            int updated = proiectDao.updateDepartamentProiect(proiect.getID(), newDepartament.getID());
            proiect.setDepartament(newDepartament);
            return ProiectDTOEntityMapper.getDTOAfterUpdateDepartament(proiect);
        }
        else
            return null;
    }

    @Override
    public EchipaDTO addProiectToEchipa(AddProiectToEchipaDTO addProiectToEchipaDTO) throws BusinessException {

        Proiect proiect = proiectDao.findProiectByNume(addProiectToEchipaDTO.getNumeProiect());
        Echipa echipa = echipaDao.findEchipaByNume(addProiectToEchipaDTO.getNumeEchipa());
        if (proiect == null || echipa == null)
            return null;
        Set<Proiect> proiects = echipa.getProiecte();
        proiects.add(proiect);
        echipa.setProiecte(proiects);
        echipaDao.save(echipa);
        EchipaDTO dtoPersisted = EchipaDTOEntityMapper.getDTOFromEchipa(echipa);
        return dtoPersisted;
    }
}
