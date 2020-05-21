package be.dao;

import be.entity.LocuriUtilizari;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface LocuriUtilizariDao extends CrudRepository<LocuriUtilizari, Integer> {

    @Query("select u.id from LocuriUtilizari.Utilizare u " +
            "inner join LocuriUtilizari lu " +
            "on u.id = lu.utilizare_id " +
            "inner join LocuriUtilizari.loc l " +
            "on l.id = loc_id where loc_id = :id and (:startDate < u.final_date and :finalDate > u.start_date)")
    int checkFree(
            @Param("id") Integer id,
            @Param("startDate") Date startDate,
            @Param("finalDate") Date finalDate
    );



}
