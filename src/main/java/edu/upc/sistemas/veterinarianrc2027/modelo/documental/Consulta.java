package edu.upc.sistemas.veterinarianrc2027.modelo.documental;

import java.time.LocalDateTime;

public class Consulta {

    private LocalDateTime fecha;
    private String motivo;
    private String diagnostico;
    private String tratamiento;

    protected Consulta() {
    }

    public Consulta(LocalDateTime fecha, String motivo,
                    String diagnostico, String tratamiento) {
        this.fecha = fecha;
        this.motivo = motivo;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
    }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }
    public String getDiagnostico() { return diagnostico; }
    public void setDiagnostico(String diagnostico) { this.diagnostico = diagnostico; }
    public String getTratamiento() { return tratamiento; }
    public void setTratamiento(String tratamiento) { this.tratamiento = tratamiento; }
}
