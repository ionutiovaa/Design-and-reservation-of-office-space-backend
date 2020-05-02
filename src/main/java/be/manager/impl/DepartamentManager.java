package be.manager.impl;

import be.dao.DepartamentDao;
import be.dao.UserDao;
import be.dto.ChangeDepartamentDTO;
import be.dto.ChangeResponsabilDepartamentDTO;
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
        Departament departament = departamentDao.findDepartamentByNume(departamentDTO.getNume());
        DepartamentDTO dtoPersisted = new DepartamentDTO();
        if (user == null)
            return null;
        if (departament == null){
            departament = new Departament();
            departament.setNume(departamentDTO.getNume());
            departament.setUser(user);
            Departament persistedDepartament = departamentDao.save(departament);
            dtoPersisted = DepartamentDTOEntityMapper.getDTOFromDepartament(persistedDepartament);
        }
        return dtoPersisted;
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

    @Override
    public DepartamentDTO changeNumeResponsabil(ChangeResponsabilDepartamentDTO changeResponsabilDepartamentDTO) throws BusinessException {
        Departament departament = departamentDao.findDepartamentByNume(changeResponsabilDepartamentDTO.getNume());
        User user = userDao.findUserByUsername(changeResponsabilDepartamentDTO.getOldResponsabil());
        User newResponsabil = userDao.findUserByUsername(changeResponsabilDepartamentDTO.getNewResponsabil());
        if (departament != null && user != null && newResponsabil != null){
            int updated = departamentDao.updateResponsabilDepartament(departament.getID(), newResponsabil.getID());
            departament.setUser(newResponsabil);
            return DepartamentDTOEntityMapper.getDTOAfterUpdateResponsabil(departament);
        }
        else
            //throw new BusinessException("Name error", "The old name is wrong");
            return null;
    }

    @Override
    public DepartamentDTO changeNumeDepartament(ChangeDepartamentDTO changeDepartamentDTO) throws BusinessException {
        Departament departament = departamentDao.findDepartamentByNume(changeDepartamentDTO.getOldNume());
        if (departament == null)
            return null;
        if (departament.getNume().equals(changeDepartamentDTO.getOldNume())){
            departament.setNume(changeDepartamentDTO.getNewNume());
            int updated = departamentDao.updateNumeDepartament(departament.getID(), departament.getNume());
            return DepartamentDTOEntityMapper.getDTOAfterUpdateNume(departament);
        }
        else
            throw new BusinessException("Name error", "The old name is wrong");
    }
}
