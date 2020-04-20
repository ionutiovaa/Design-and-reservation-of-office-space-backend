package be.dao;

import be.entity.Client;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ClientDao extends CrudRepository<Client, Integer> {

    Client findAllByID(Integer id);

    List<Client> findAll();

    Client findClientByNume(String nume);

    Client findClientByID(Integer id);

    /*@Override
    void delete(Client entity);*/

    void deleteClientByNume(String nume);

    @Transactional
    @Modifying
    @Query("UPDATE Client c " +
            "SET nume = :newNume " +
            "WHERE ID = :clientID")
    int updateNumeClient(@Param("clientID") Integer clientID,
                        @Param("newNume") String newNume);
}
