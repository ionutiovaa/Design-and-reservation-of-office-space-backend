package be.manager.impl;

import be.dao.EtajDao;
import be.dao.SediuDao;
import be.entity.Etaj;
import be.entity.Loc;
import be.entity.Sediu;
import be.exceptions.BusinessException;
import be.manager.remote.EtajMangerRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EtajManager implements EtajMangerRemote {

    @Autowired
    SediuDao sediuDao;

    @Autowired
    EtajDao etajDao;

    @Override
    public Etaj insertEtaj() throws BusinessException {
        Sediu sediu = sediuDao.findSediuByID(1);
        List<Etaj> etaje = sediu.getEtaje();
        List<Loc> locuri = new ArrayList<>();
        Etaj etaj = new Etaj(etaje.size() + 1, locuri, sediu);
        etajDao.save(etaj);
        etaje.add(etaj);
        sediuDao.save(sediu);
        Etaj etajPersisted = new Etaj();
        etajPersisted.setID(etaj.getID());
        etajPersisted.setNumar(etaj.getNumar());
        etajPersisted.setLocuri(etaj.getLocuri());
        return etajPersisted;
    }

    @Override
    public void deleteLastFloor() throws BusinessException {
        Sediu sediu = sediuDao.findSediuByID(1);
        List<Etaj> etaje = sediu.getEtaje();
        Integer etajNumber = etaje.size();
        Etaj etaj = etajDao.findEtajByNumar(etajNumber);
        etaje.remove(etaj);
        etajDao.deleteEtajByNumar(etajNumber);
    }

    @Override
    public Integer getNumberOfFloorsBySediu(Integer id) throws BusinessException {
        Sediu sediu = sediuDao.findSediuByID(id);
        return sediu.getEtaje().size();
    }
}
