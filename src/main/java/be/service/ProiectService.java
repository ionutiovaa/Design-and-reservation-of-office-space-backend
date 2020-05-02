package be.service;

import be.entity.Proiect;

import java.util.List;

public interface ProiectService {
    void save(Proiect proiect);
    void delete(Proiect proiect);
    List<Proiect> findAllProiects();
}
