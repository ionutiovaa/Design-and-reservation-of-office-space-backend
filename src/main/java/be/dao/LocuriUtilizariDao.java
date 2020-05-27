package be.dao;

import be.entity.LocuriUtilizari;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface LocuriUtilizariDao extends CrudRepository<LocuriUtilizari, Integer> {

    /*@Query(value = "select u.id from Utilizare u " +
            "inner join LocuriUtilizari lu " +
            "on u.id = lu.utilizare.id " +
            "inner join LocuriUtilizari.loc l " +
            "on l.id = lu.loc.id where lu.loc.id = :id and (:startDate < u.finalDate and :finalDate > u.startDate)", nativeQuery = true)
    int checkFree(
            @Param("id") Integer id,
            @Param("startDate") Date startDate,
            @Param("finalDate") Date finalDate
    );


    @Query(value = "select u.id from Utilizare u " +
            "inner join LocuriUtilizari lu " +
            "on u.id = lu.utilizare.id " +
            "inner join LocuriUtilizari.loc l " +
            "on l.id = lu.loc.id where lu.loc.id = :id and (:startDate < u.finalDate and :finalDate > u.startDate)", nativeQuery = true)
    int checkFree(
            @Param("id") Integer id
    );

    @Query("select lu.utilizare.id from LocuriUtilizari lu where lu.loc.id = : id")
    List<Integer> getIds(@Param("id") Integer id);*/



}
