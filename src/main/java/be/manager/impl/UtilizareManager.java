package be.manager.impl;

import be.dao.LocDao;
import be.dao.LocuriUtilizariDao;
import be.dao.UserDao;
import be.dao.UtilizareDao;
import be.dto.FreeTimeDTO;
import be.dto.LocDTO;
import be.dto.UtilizareDTO;
import be.dtoEntityMappers.LocDTOEntityMapper;
import be.entity.Loc;
import be.entity.User;
import be.entity.Utilizare;
import be.exceptions.BusinessException;
import be.manager.remote.UtilizareManagerRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@Component
public class UtilizareManager implements UtilizareManagerRemote {

    @Autowired
    private UtilizareDao utilizareDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private LocDao locDao;

    @Autowired
    private LocuriUtilizariDao locuriUtilizariDao;

    private Logger logger = Logger.getLogger(UtilizareManager.class.getName());

    @Override
    public LocDTO insertUtilizare(UtilizareDTO utilizareDTO) throws BusinessException {
        //List<FreeTimeDTO> freeTimeList = locDao.findAllFreeTimes(utilizareDTO.getIdLoc());
        //int free = locDao.checkFree(utilizareDTO.getIdLoc(), utilizareDTO.getStartDate(), utilizareDTO.getFinalDate());
        //if (free != 0)
        //    return null;
        int free = locuriUtilizariDao.checkFree(utilizareDTO.getIdLoc(), utilizareDTO.getStartDate(), utilizareDTO.getFinalDate());
        if (free != 0)
            return null;
        User user = userDao.findUserByUsername(utilizareDTO.getUsername());
        Loc loc = locDao.findAllByID(utilizareDTO.getIdLoc());
        if (user == null)
            throw new BusinessException("Not found", "The username is wrong");
        Utilizare utilizare = new Utilizare();
        utilizare.setUser(user);
        utilizare.setStartDate(utilizareDTO.getStartDate());
        utilizare.setFinalDate(utilizareDTO.getFinalDate());
        utilizareDao.save(utilizare);
        Set<Utilizare> utilizari = loc.getUtilizari();
        utilizari.add(utilizare);
        locDao.save(loc);
        LocDTO dtoPersisted = LocDTOEntityMapper.getDTOFromLoc(loc);
        return dtoPersisted;
    }

    @Override
    public List<UtilizareDTO> findAllUtilizari() {
        return null;
    }

    @Override
    public void deleteUtilizareById(Integer id) throws BusinessException {

    }
}
