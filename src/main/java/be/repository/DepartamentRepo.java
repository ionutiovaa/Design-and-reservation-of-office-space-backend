package be.repository;

import be.entity.Departament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartamentRepo extends JpaRepository<Departament, Integer> {
}
