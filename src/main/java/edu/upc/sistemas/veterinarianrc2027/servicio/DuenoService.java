package edu.upc.sistemas.veterinarianrc2027.servicio;

import edu.upc.sistemas.veterinarianrc2027.modelo.Dueno;
import edu.upc.sistemas.veterinarianrc2027.repositorio.DuenoRepository;
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
        if(duenoRepository.existsByEmailIgnoreCase(email)){
            throw new IllegalArgumentException("El email existe en el sistema");
        }
        return duenoRepository.save(new Dueno(nombre, telefono, email));
    }

    @Transactional(readOnly = true)
    public List<Dueno> listar(){
        return duenoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Dueno buscarPorId(Long id){
        return duenoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No existe el registro en el sistema") );
    }

}
