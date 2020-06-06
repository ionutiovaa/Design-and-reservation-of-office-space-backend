package be.manager.impl;

import be.dao.EchipaDao;
import be.dao.ProiectDao;
import be.dto.AddProiectToEchipaDTO;
import be.dto.ChangeNumeEchipaDTO;
import be.dto.EchipaDTO;
import be.dto.ProiectDTO;
import be.dtoEntityMappers.EchipaDTOEntityMapper;
import be.dtoEntityMappers.ProiectDTOEntityMapper;
import be.entity.Echipa;
import be.entity.Proiect;
import be.exceptions.BusinessException;
import be.manager.remote.EchipaManagerRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@Component
public class EchipaManager implements EchipaManagerRemote {

    @Autowired
    private EchipaDao echipaDao;

    @Autowired
    private ProiectDao proiectDao;

    private Logger logger = Logger.getLogger(EchipaManager.class.getName());

    @Override
    public EchipaDTO insertEchipa(EchipaDTO echipaDTO) throws BusinessException {
        Echipa echipa = echipaDao.findEchipaByNume(echipaDTO.getNume());
        if (echipa != null)
            return null;
        EchipaDTO dtoPersisted = new EchipaDTO();
        echipa = new Echipa();
        echipa.setNume(echipaDTO.getNume());
        Echipa persistedEchipa = echipaDao.save(echipa);
        dtoPersisted = EchipaDTOEntityMapper.getDTOFromEchipa(persistedEchipa);
        return dtoPersisted;
    }

    @Override
    public List<EchipaDTO> findAllEchipe() {
        List<Echipa> echipe = echipaDao.findAll();
        return EchipaDTOEntityMapper.getAllEchipe(echipe);
    }

    @Override
    public EchipaDTO deleteEchipaByNume(String nume) throws Exception {
        Echipa echipa = echipaDao.findEchipaByNume(nume);
        try{
            if (echipa != null)
                echipaDao.delete(echipa);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return EchipaDTOEntityMapper.getDTOFromEchipa(echipa);
    }

    @Override
    public EchipaDTO findEchipaByNume(String nume) throws BusinessException {
        Echipa echipa = echipaDao.findEchipaByNume(nume);
        EchipaDTO echipaDTO = EchipaDTOEntityMapper.getDTOFromEchipa(echipa);
        return echipaDTO;
    }

    @Override
    public EchipaDTO changeNumeEchipa(ChangeNumeEchipaDTO changeNumeEchipaDTO) throws BusinessException {
        Echipa echipa = echipaDao.findEchipaByNume(changeNumeEchipaDTO.getOldName());
        if (echipa == null)
            return null;
        if (echipa.getNume().equals(changeNumeEchipaDTO.getOldName())){
            echipa.setNume(changeNumeEchipaDTO.getNewName());
            int updated = echipaDao.updateNumeEchipa(echipa.getID(), echipa.getNume());
            return EchipaDTOEntityMapper.getDTOAfterUpdateNume(echipa);
        }
        else
            throw new BusinessException("Name error", "The old name is wrong");
    }
}
