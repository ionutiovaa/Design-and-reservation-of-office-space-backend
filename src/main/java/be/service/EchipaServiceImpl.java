package be.service;

import be.entity.Echipa;
import be.repository.EchipaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EchipaServiceImpl implements EchipaService {

    @Autowired
    EchipaRepository echipaRepository;

    @Override
    public void save(Echipa echipa) {
        echipaRepository.save(echipa);
    }

    @Override
    public void delete(Echipa echipa) {
        echipaRepository.delete(echipa);
    }

    @Override
    public List<Echipa> findAllEchipe() {
        return echipaRepository.findAll();
    }
}
