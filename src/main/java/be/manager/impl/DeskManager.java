/*
package be.manager.impl;

import be.dao.DeskDao;
import be.dto.DeskDTO;
import be.dto.FreeTimeDTO;
import be.dtoEntityMappers.DeskDTOEntityMapper;
import be.entity.Desk;
import be.exceptions.BusinessException;
import be.manager.remote.DeskManagerRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

@Component
public class DeskManager implements DeskManagerRemote {

    @Autowired
    private DeskDao deskDao;

    public DeskDao getDeskDao() {
        return deskDao;
    }

    private Logger logger = Logger.getLogger(DeskManager.class.getName());

    @Override
    public DeskDTO insertDesk(DeskDTO deskDTO) throws BusinessException {
        Desk desk = deskDao.findAllByID(deskDTO.getID());
        if (desk != null)
            return null;
        DeskDTO dtoPersisted = new DeskDTO();
        desk = new Desk();
        desk.setQrCode(deskDTO.getQrCode());
        desk.setPozitie(deskDTO.getPozitie());
        desk.setValue(deskDTO.getValue());
        Desk persistedDesk = deskDao.save(desk);
        dtoPersisted = DeskDTOEntityMapper.getDTOFromDesk(persistedDesk);
        return dtoPersisted;
    }

    @Override
    public List<DeskDTO> findAllDesks() {
        List<Desk> desks = deskDao.findAll();
        return DeskDTOEntityMapper.getAllDesks(desks);
    }

    @Override
    public void deleteDeskById(Integer id) throws BusinessException {
        Desk desk = deskDao.findAllByID(id);
        deskDao.delete(desk);
    }

    @Override
    public List<FreeTimeDTO> findAllFreeTimes(Integer id) {
        return null;
    }
}
*/
