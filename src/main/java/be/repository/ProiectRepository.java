package be.repository;

import be.entity.Proiect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProiectRepository extends JpaRepository<Proiect, Integer> {
}
