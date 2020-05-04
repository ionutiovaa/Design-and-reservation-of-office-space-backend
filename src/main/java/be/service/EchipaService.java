package be.service;

import be.entity.Echipa;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface EchipaService {

    void save(Echipa echipa);
    void delete(Echipa echipa);
    List<Echipa> findAllEchipe();

}
