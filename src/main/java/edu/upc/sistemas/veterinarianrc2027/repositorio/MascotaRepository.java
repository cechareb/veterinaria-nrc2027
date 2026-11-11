package edu.upc.sistemas.veterinarianrc2027.repositorio;

import edu.upc.sistemas.veterinarianrc2027.modelo.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MascotaRepository extends JpaRepository<Mascota,Long> {
}
