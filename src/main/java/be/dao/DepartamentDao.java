package be.dao;

import be.entity.Departament;
import be.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DepartamentDao extends CrudRepository<Departament, Integer> {

    Departament findAllByID(Integer id);

    Departament findDepartamentByNume(String nume);

    List<Departament> findAll();

    @Transactional
    @Modifying
    @Query("UPDATE Departament d " +
            "SET nume = :newNume " +
            "WHERE ID = :departamentID")
    int updateNumeDepartament(@Param("departamentID") Integer departamentID,
                              @Param("newNume") String newNume);


    @Transactional
    @Modifying
    @Query("UPDATE Departament d " +
            "SET id_responsabil = :newId " +
            "WHERE ID = :departamentID")
    int updateResponsabilDepartament(@Param("departamentID") Integer departamentID,
                                    @Param("newId") Integer newId);
}
