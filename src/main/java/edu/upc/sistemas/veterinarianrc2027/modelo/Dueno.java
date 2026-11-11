package edu.upc.sistemas.veterinarianrc2027.modelo;

import jakarta.persistence.*;

@Entity
@Table
public class Dueno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 80)
    private String nombre;
    @Column(nullable = false, length = 20)
    private String telefono;
    @Column(nullable = false, length = 100)
    private String email;

    protected Dueno() {}

    public Dueno(String nombre, String telefono, String email) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
