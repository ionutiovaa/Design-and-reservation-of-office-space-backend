/*
package be.dao;

import be.entity.QRCode;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface QRCodeDao extends CrudRepository<QRCode, Integer> {

    QRCode findAllByID(Integer id);

    List<QRCode> findAll();

    QRCode findQRCodeByNume(String nume);

    @Transactional
    @Modifying
    @Query("UPDATE QRCode q " +
            "SET nume = :newNume " +
            "WHERE ID = :qrcodeID")
    int updateNumeClient(@Param("qrcodeID") Integer qrcodeID,
                         @Param("newNume") String newNume);

}
*/
