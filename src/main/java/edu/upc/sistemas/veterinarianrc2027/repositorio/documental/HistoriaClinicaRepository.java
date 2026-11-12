package edu.upc.sistemas.veterinarianrc2027.repositorio.documental;

import edu.upc.sistemas.veterinarianrc2027.modelo.documental.HistoriaClinica;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface HistoriaClinicaRepository extends MongoRepository<HistoriaClinica, String> {
    Optional<HistoriaClinica> findByMascotaId(Long mascotaId);
}
