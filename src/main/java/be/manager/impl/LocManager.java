package be.manager.impl;

import be.dao.LocDao;
import be.dto.FreeTimeDTO;
import be.dto.LocDTO;
import be.dtoEntityMappers.LocDTOEntityMapper;
import be.entity.Loc;
import be.exceptions.BusinessException;
import be.manager.remote.LocManagerRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.logging.Logger;

@Component
public class LocManager implements LocManagerRemote {

    @Autowired
    private LocDao locDao;

    public LocDao getLocDao() {
        return locDao;
    }

    private Logger logger = Logger.getLogger(LocManager.class.getName());

    @Override
    public LocDTO insertLoc(LocDTO locDTO) throws BusinessException {
        Loc loc = locDao.findAllByID(locDTO.getID());
        if (loc != null)
            return null;
        LocDTO dtoPersisted = new LocDTO();
        loc = new Loc();
        loc.setQrCode(locDTO.getQrCode());
        Loc persistedLoc = locDao.save(loc);
        dtoPersisted = LocDTOEntityMapper.getDTOFromLoc(persistedLoc);
        return dtoPersisted;
    }

    @Override
    public List<LocDTO> findAllLocuri() {
        List<Loc> locuri = locDao.findAll();
        return LocDTOEntityMapper.getAllLocuri(locuri);
    }

    @Override
    public void deleteLocById(Integer id) throws BusinessException {
        Loc loc = locDao.findAllByID(id);
        locDao.delete(loc);
    }

    @Override
    public List<FreeTimeDTO> findAllFreeTimes(Integer id) {
        return null;
    }

    /*@PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<FreeTimeDTO> findAllFreeTimes(Integer id) {

        String query = "select u.start_date, u.final_date from utilizari u inner join locuri_utilizari lu on u.id = lu.utilizare_id "+
                "inner join locuri l on l.id = lu.loc_id where lu.loc_id = ?1";

        List<?> times = entityManager.createQuery(query).setParameter(1, id).getResultList();
    }*/
}
