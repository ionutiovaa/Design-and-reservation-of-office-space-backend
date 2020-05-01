package be.service;

import be.entity.Departament;
import be.repository.DepartamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartamentServiceImpl implements DepartamentService {

    @Autowired
    DepartamentRepository departamentRepository;


    @Override
    public void save(Departament departament) {
        departamentRepository.save(departament);
    }

    @Override
    public void delete(Departament departament) {
        departamentRepository.delete(departament);
    }

    @Override
    public List<Departament> findAllDepartaments() {
        return departamentRepository.findAll();
    }
}
