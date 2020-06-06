package be.dao;

import be.entity.Echipa;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface EchipaDao extends CrudRepository<Echipa, Integer> {

    List<Echipa> findAll();

    Echipa findEchipaByNume(String nume);

    @Transactional
    @Modifying
    @Query("UPDATE Echipa e " +
            "SET nume = :newNume " +
            "WHERE ID = :echipaID")
    int updateNumeEchipa(@Param("echipaID") Integer echipaID,
                         @Param("newNume") String newNume);

}
