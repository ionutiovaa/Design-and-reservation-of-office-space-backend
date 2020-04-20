package be.dao;

import be.entity.Token;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TokenDao extends CrudRepository<Token, Integer> {

    //@Query(value = "SELECT t.username FROM Token t WHERE t.token = ?1")
    Token findByToken(String token);

    Token findByUsername(String username);

    Token findByID(Integer id);

    @Query(value = "SELECT t from Token t where t.username = ?1")
    Token login(String username);

    @Transactional
    @Modifying
    @Query("UPDATE Token t " + "set token = :token " + "WHERE ID = :id " + "AND username = :username")
    int update(@Param("id") Integer id,
               @Param("username") String username,
               @Param("token") String token
    );

}
