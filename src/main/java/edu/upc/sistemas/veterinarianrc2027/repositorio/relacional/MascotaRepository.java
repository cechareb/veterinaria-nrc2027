package edu.upc.sistemas.veterinarianrc2027.repositorio.relacional;

import edu.upc.sistemas.veterinarianrc2027.modelo.relacional.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MascotaRepository extends JpaRepository<Mascota, Long> {
}
