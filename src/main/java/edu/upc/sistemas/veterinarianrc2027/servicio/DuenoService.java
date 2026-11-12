package edu.upc.sistemas.veterinarianrc2027.servicio;

import edu.upc.sistemas.veterinarianrc2027.modelo.relacional.Dueno;
import edu.upc.sistemas.veterinarianrc2027.repositorio.relacional.DuenoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class DuenoService {

    private final DuenoRepository duenoRepository;

    public DuenoService(DuenoRepository duenoRepository) {
        this.duenoRepository = duenoRepository;
    }

    @Transactional
    public Dueno registrar(String nombre, String telefono, String email) {
        if (duenoRepository.existsByEmailIgnoreCase(email)) {
            throw new IllegalArgumentException("Ya existe un dueño con ese correo electrónico.");
        }
        return duenoRepository.save(new Dueno(nombre, telefono, email));
    }

    @Transactional(readOnly = true)
    public List<Dueno> listar() {
        return duenoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Dueno buscarPorId(Long id) {
        return duenoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "No existe un dueño con el ID " + id + "."));
    }
}
