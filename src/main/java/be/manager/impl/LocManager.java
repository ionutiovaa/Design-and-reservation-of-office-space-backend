package be.manager.impl;

import be.dao.EtajDao;
import be.dao.LocDao;
import be.dao.SediuDao;
import be.dto.FreeTimeDTO;
import be.dto.LocDTO;
import be.dtoEntityMappers.LocDTOEntityMapper;
import be.entity.Etaj;
import be.entity.Loc;
import be.entity.Sediu;
import be.exceptions.BusinessException;
import be.manager.remote.LocManagerRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Logger;

@Component
public class LocManager implements LocManagerRemote {

    @Autowired
    private LocDao locDao;

    @Autowired
    private SediuDao sediuDao;

    @Autowired
    private EtajDao etajDao;

    public LocDao getLocDao() {
        return locDao;
    }

    private Logger logger = Logger.getLogger(LocManager.class.getName());

    /*@Override
    public LocDTO insertLoc(LocDTO locDTO) throws BusinessException {
        Loc loc = locDao.findLocByPozitie(locDTO.getPozitie());
        if (loc != null)
            return null;
        LocDTO dtoPersisted = new LocDTO();
        loc = new Loc();
        loc.setQrCode(locDTO.getQrCode());
        loc.setPozitie(locDTO.getPozitie());
        loc.setValue(locDTO.getValue());
        Loc persistedLoc = locDao.save(loc);
        dtoPersisted = LocDTOEntityMapper.getDTOFromLoc(persistedLoc);
        return dtoPersisted;
    }*/

    @Override
    public LocDTO insertLoc(LocDTO locDTO) throws BusinessException {
        //Sediu sediu = sediuDao.findSediuByID(1);
        String[] pozitii = locDTO.getPozitie().split(":");
        Etaj etaj = etajDao.findEtajByNumar(Integer.parseInt(pozitii[0]));
        Loc loc = new Loc(locDTO.getPozitie(), locDTO.getQrCode(), locDTO.getValue(), etaj, new HashSet<>());
        locDao.save(loc);
        List<Loc> locuri = etaj.getLocuri();
        locuri.add(loc);
        etajDao.save(etaj);
        return LocDTOEntityMapper.getDTOFromLoc(loc);
    }

    @Override
    public List<LocDTO> findAllLocuri() {
        List<Loc> locuri = locDao.findAll();
        return LocDTOEntityMapper.getAllLocuri(locuri);
    }

    @Override
    public void deleteLocByPosition(String position) throws BusinessException {
        Integer etajNumber = Integer.valueOf(position.split(":")[0]);
        Etaj etaj = etajDao.findEtajByNumar(etajNumber);
        Loc loc = locDao.findLocByPozitie(position);
        etaj.getLocuri().remove(loc);
        etajDao.save(etaj);
        locDao.deleteLocByPozitie(position);
        /*Loc loc = locDao.findLocByPozitie(position);
        locDao.delete(loc);*/
    }

    @Override
    public List<FreeTimeDTO> findAllFreeTimes(Integer id) {
        return null;
    }

}
