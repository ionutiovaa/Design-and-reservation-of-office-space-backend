package be.dao;

import be.entity.Utilizare;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UtilizareDao extends CrudRepository<Utilizare, Integer> {

    Utilizare findAllByID(Integer id);

    List<Utilizare> findAll();

}
