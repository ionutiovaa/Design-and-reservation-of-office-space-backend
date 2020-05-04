package be.repository;

import be.dto.AddProiectToEchipaDTO;
import be.entity.Echipa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface EchipaRepository extends JpaRepository<Echipa, Integer> {
}
