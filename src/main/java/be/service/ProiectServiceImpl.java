package be.service;

import be.entity.Proiect;
import be.repository.ProiectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProiectServiceImpl implements ProiectService {

    @Autowired
    ProiectRepository proiectRepository;

    @Override
    public void save(Proiect proiect) {
        proiectRepository.save(proiect);
    }

    @Override
    public void delete(Proiect proiect) {
        proiectRepository.delete(proiect);
    }

    @Override
    public List<Proiect> findAllProiects() {
        return proiectRepository.findAll();
    }
}
