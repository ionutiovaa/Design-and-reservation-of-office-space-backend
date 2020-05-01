package be.manager.impl;

import be.dao.DepartamentDao;
import be.dao.UserDao;
import be.dto.DepartamentDTO;
import be.dtoEntityMappers.DepartamentDTOEntityMapper;
import be.entity.Departament;
import be.entity.User;
import be.exceptions.BusinessException;
import be.manager.remote.DepartamentManagerRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Component
public class DepartamentManager implements DepartamentManagerRemote {

    @Autowired
    private DepartamentDao departamentDao;

    @Autowired
    private UserDao userDao;

    public DepartamentDao getDepartamentDao() {
        return departamentDao;
    }

    private Logger logger = Logger.getLogger(DepartamentManager.class.getName());

    @Override
    public DepartamentDTO insertDepartament(DepartamentDTO departamentDTO) throws BusinessException {
        User user = userDao.findUserByUsername(departamentDTO.getUser_responsabil().getUsername());
        if (user != null){
            Departament departament = new Departament();
            departament.setNume(departamentDTO.getNume());
            departament.setUser(user);
            Departament persistedDepartament = departamentDao.save(departament);
            DepartamentDTO dtoPersisted = DepartamentDTOEntityMapper.getDTOFromDepartament(persistedDepartament);
            return dtoPersisted;
        }

        return null;
    }

    @Override
    public List<DepartamentDTO> findAllDepartaments() {
        List<Departament> departaments = departamentDao.findAll();
        return DepartamentDTOEntityMapper.getAllClients(departaments);
    }

    @Override
    public DepartamentDTO deleteDepartamentByNume(String nume) throws Exception {
        Departament departament = departamentDao.findDepartamentByNume(nume);
        try{
            if (departament != null){
                departamentDao.delete(departament);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return DepartamentDTOEntityMapper.getDTOFromDepartament(departament);
    }

    @Override
    public DepartamentDTO findDepartamentByNume(String nume) throws BusinessException {
        Departament departament = departamentDao.findDepartamentByNume(nume);
        DepartamentDTO departamentDTO = DepartamentDTOEntityMapper.getDTOFromDepartament(departament);
        return departamentDTO;
    }
}
