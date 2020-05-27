package be.dao;

import be.dto.FreeTimeDTO;
import be.entity.Loc;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface LocDao extends CrudRepository<Loc, Integer> {

    Loc findAllByID(Integer id);

    Loc findLocByPozitie(String pozitie);

    List<Loc> findAll();

    @Transactional
    void deleteLocByPozitie(String pozitie);

    /*@Query("select cast(start_date as time), cast(final_date as time) from Loc.utilizari u " +
            "join locuri_utilizari lu " +
            "on u.id = lu.utilizare_id " +
            "join Loc l " +
            "on l.ID = loc_id where loc_id = :id")
    List<FreeTimeDTO> findAllFreeTimes(
            @Param("id") Integer id
            );

    @Query("select u.id from utilizari u " +
            "inner join locuri_utilizari lu " +
            "on u.id = lu.utilizare_id " +
            "inner join locuri l " +
            "on l.id = loc_id where loc_id = :id and (:startDate < u.final_date and :finalDate > u.start_date)")
    int checkFree(
            @Param("id") Integer id,
            @Param("startDate") Date startDate,
            @Param("finalDate") Date finalDate
            );*/


}
