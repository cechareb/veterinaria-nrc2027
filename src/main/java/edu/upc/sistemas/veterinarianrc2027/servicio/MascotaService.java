package edu.upc.sistemas.veterinarianrc2027.servicio;

import edu.upc.sistemas.veterinarianrc2027.modelo.Dueno;
import edu.upc.sistemas.veterinarianrc2027.modelo.Mascota;
import edu.upc.sistemas.veterinarianrc2027.repositorio.MascotaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class MascotaService {

    private final MascotaRepository mascotaRepository;
    private final DuenoService duenoService;

    public MascotaService(MascotaRepository mascotaRepository, DuenoService duenoService) {
        this.mascotaRepository = mascotaRepository;
        this.duenoService = duenoService;
    }

    @Transactional
    public Mascota registrar(String nombre, String especie, String raza, LocalDate fechaNacimiento, Long duenoId){
        if (fechaNacimiento == null || fechaNacimiento.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Fecha nacimiento no puede ser null o futura");
        }
        Dueno dueno = duenoService.buscarPorId(duenoId);
        return mascotaRepository.save(new Mascota(nombre, especie, raza, fechaNacimiento, dueno));
    }

    @Transactional(readOnly = true)
    public List<Mascota> listar(){
        return mascotaRepository.findAll();
    }
}
