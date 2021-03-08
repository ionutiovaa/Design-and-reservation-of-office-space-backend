package be.dao;

import be.entity.Loc;
import be.entity.User;
import be.entity.Utilizare;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Repository
public interface UtilizareDao extends CrudRepository<Utilizare, Integer> {

    List<Utilizare> findAll();

    @Query(nativeQuery = true, value = "SELECT TIME_FORMAT(start_date, \"%H:%i:%s\") from utilizari where id_loc = :id_loc and DATE_FORMAT(start_date, '%Y-%m-%d') = :selected_date")
    List<String> findAllSchedulesStartDate(
            @Param("selected_date") String selected_date,
            @Param("id_loc") Integer id_loc
    );

    @Query(nativeQuery = true, value = "SELECT TIME_FORMAT(final_date, \"%H:%i:%s\") from utilizari where id_loc = :id_loc and DATE_FORMAT(start_date, '%Y-%m-%d') = :selected_date")
    List<String> findAllSchedulesEndDate(
            @Param("selected_date") String selected_date,
            @Param("id_loc") Integer id_loc
    );

    @Query(nativeQuery = true, value = "SELECT user from utilizari where id_loc = :id_loc and DATE_FORMAT(start_date, '%Y-%m-%d') = :selected_date")
    List<Integer> getAllUserIds(
            @Param("selected_date") String selected_date,
            @Param("id_loc") Integer id_loc
    );

    @Query(nativeQuery = true, value = "delete from utilizari where user = :user_id")
    @Modifying
    @Transactional
    void deleteUtilizareByUser_id(@Param("user_id") Integer user_id);

    @Query(nativeQuery = true, value = "select id from utilizari where user = :user_id")
    List<Integer> getIdUtilizariByUser(@Param("user_id") Integer user_id);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "delete from locuri_utilizari where utilizari_id = :id")
    Integer deleteFromLocuriUtilizariByUtilizare_id(@Param("id") Integer id);

    List<Utilizare> findAllByLocAndStartDateIsLessThanAndFinalDateIsGreaterThan(Loc id_loc, Date final_date, Date start_date);

    Utilizare getUtilizareByStartDateAndLocAndUser_ID(Date start_date, Loc id_loc, Integer user);

    @Transactional
    @Modifying
    void deleteUtilizareByID(Integer id);



    Utilizare getUtilizareByFinalDateAndStartDateAndLocAndUser(Date final_date, Date start_date, Loc id_loc, User user);

}
