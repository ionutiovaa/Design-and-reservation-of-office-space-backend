package be.dao;

import be.entity.Etaj;
import be.entity.Loc;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface LocDao extends CrudRepository<Loc, Integer> {

    Loc findLocByPozitie(String pozitie);

    List<Loc> findAllByEtaj(Etaj etaj);

    List<Loc> findAll();

    @Transactional
    void deleteLocByPozitie(String pozitie);

}
