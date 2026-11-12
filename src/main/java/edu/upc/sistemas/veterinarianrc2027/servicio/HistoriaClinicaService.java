package edu.upc.sistemas.veterinarianrc2027.servicio;

import edu.upc.sistemas.veterinarianrc2027.modelo.documental.Consulta;
import edu.upc.sistemas.veterinarianrc2027.modelo.documental.HistoriaClinica;
import edu.upc.sistemas.veterinarianrc2027.repositorio.documental.HistoriaClinicaRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class HistoriaClinicaService {

    private final HistoriaClinicaRepository historiaRepository;
    private final MascotaService mascotaService;

    public HistoriaClinicaService(HistoriaClinicaRepository historiaRepository,
                                  MascotaService mascotaService) {
        this.historiaRepository = historiaRepository;
        this.mascotaService = mascotaService;
    }

    public HistoriaClinica registrarConsulta(Long mascotaId, String motivo,
                                             String diagnostico, String tratamiento) {
        // Comprueba primero en PostgreSQL que la mascota existe.
        mascotaService.buscarPorId(mascotaId);

        HistoriaClinica historia = historiaRepository.findByMascotaId(mascotaId)
                .orElseGet(() -> new HistoriaClinica(mascotaId));
        historia.agregarConsulta(new Consulta(
                LocalDateTime.now(), motivo, diagnostico, tratamiento));
        return historiaRepository.save(historia);
    }

    public HistoriaClinica buscarPorMascotaId(Long mascotaId) {
        mascotaService.buscarPorId(mascotaId);
        return historiaRepository.findByMascotaId(mascotaId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "La mascota todavía no tiene una historia clínica."));
    }
}
