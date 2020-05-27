package be.dao;

import be.entity.Sediu;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface SediuDao extends CrudRepository<Sediu, Integer> {

    Sediu findSediuByID(Integer id);

    Sediu findSediuByAdresa(String adresa);

    Sediu findSediuByNume(String nume);

    @Transactional
    @Modifying
    @Query("UPDATE Sediu s SET dimensions = :dimensions " +
            "WHERE ID = :id")
    int updateDimensions(@Param("dimensions") String dimensions,
               @Param("id") Integer id);

}
