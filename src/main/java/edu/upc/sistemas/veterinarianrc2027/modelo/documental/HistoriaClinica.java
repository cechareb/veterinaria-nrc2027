package edu.upc.sistemas.veterinarianrc2027.modelo.documental;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "historias_clinicas")
public class HistoriaClinica {

    @Id
    private String id;

    @Indexed(unique = true)
    @Field("mascota_id")
    private Long mascotaId;

    private List<Consulta> consultas = new ArrayList<>();

    protected HistoriaClinica() {
    }

    public HistoriaClinica(Long mascotaId) {
        this.mascotaId = mascotaId;
    }

    public void agregarConsulta(Consulta consulta) {
        if (consultas == null) {
            consultas = new ArrayList<>();
        }
        consultas.add(consulta);
    }

    public String getId() { return id; }
    public Long getMascotaId() { return mascotaId; }
    public void setMascotaId(Long mascotaId) { this.mascotaId = mascotaId; }
    public List<Consulta> getConsultas() { return consultas; }
    public void setConsultas(List<Consulta> consultas) { this.consultas = consultas; }
}
