package edu.upc.sistemas.veterinarianrc2027.repositorio;

import edu.upc.sistemas.veterinarianrc2027.modelo.Dueno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DuenoRepository extends JpaRepository<Dueno,Long> {

    boolean existsByEmailIgnoreCase(String email);
}
