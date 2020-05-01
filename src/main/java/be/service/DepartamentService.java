package be.service;

import be.entity.Departament;

import java.util.List;

public interface DepartamentService {

    void save(Departament departament);
    void delete(Departament departament);
    List<Departament> findAllDepartaments();

}
