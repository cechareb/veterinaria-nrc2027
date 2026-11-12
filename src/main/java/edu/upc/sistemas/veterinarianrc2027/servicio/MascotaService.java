package edu.upc.sistemas.veterinarianrc2027.servicio;

import edu.upc.sistemas.veterinarianrc2027.modelo.relacional.Dueno;
import edu.upc.sistemas.veterinarianrc2027.modelo.relacional.Mascota;
import edu.upc.sistemas.veterinarianrc2027.repositorio.relacional.MascotaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class MascotaService {

    private final MascotaRepository mascotaRepository;
    private final DuenoService duenoService;

    public MascotaService(MascotaRepository mascotaRepository,
                          DuenoService duenoService) {
        this.mascotaRepository = mascotaRepository;
        this.duenoService = duenoService;
    }

    @Transactional
    public Mascota registrar(String nombre, String especie, String raza,
                             LocalDate fechaNacimiento, Long duenoId) {
        if (fechaNacimiento == null || fechaNacimiento.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser futura.");
        }
        Dueno dueno = duenoService.buscarPorId(duenoId);
        return mascotaRepository.save(
                new Mascota(nombre, especie, raza, fechaNacimiento, dueno));
    }

    @Transactional(readOnly = true)
    public List<Mascota> listar() {
        return mascotaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Mascota buscarPorId(Long id) {
        return mascotaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "No existe una mascota con el ID " + id + "."));
    }
}
