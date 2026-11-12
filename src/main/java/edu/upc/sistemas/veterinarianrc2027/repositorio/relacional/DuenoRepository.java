package edu.upc.sistemas.veterinarianrc2027.repositorio.relacional;

import edu.upc.sistemas.veterinarianrc2027.modelo.relacional.Dueno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DuenoRepository extends JpaRepository<Dueno, Long> {
    boolean existsByEmailIgnoreCase(String email);
}
