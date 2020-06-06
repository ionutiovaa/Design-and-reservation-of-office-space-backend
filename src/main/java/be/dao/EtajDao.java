package be.dao;

import be.entity.Etaj;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface EtajDao extends CrudRepository<Etaj, Integer> {

    Etaj findEtajByNumar(Integer numar);

    @Transactional
    void deleteEtajByNumar(Integer numar);

}
