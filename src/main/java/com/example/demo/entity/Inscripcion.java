package com.example.demo.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Inscripcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "aspirante_id")
    private Aspirante aspirante;

    @ManyToOne
    @JoinColumn(name = "oportunidad_id")
    private Oportunidad oportunidad;

    private String estado;
    private LocalDateTime fechaInscripcion;
    private String observaciones;

    public Inscripcion() {
        this.fechaInscripcion = LocalDateTime.now();
        this.estado = "PENDIENTE";
    }

    public Inscripcion(Aspirante aspirante, Oportunidad oportunidad) {
        this.aspirante = aspirante;
        this.oportunidad = oportunidad;
        this.fechaInscripcion = LocalDateTime.now();
        this.estado = "PENDIENTE";
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Aspirante getAspirante() {
        return aspirante;
    }

    public void setAspirante(Aspirante aspirante) {
        this.aspirante = aspirante;
    }

    public Oportunidad getOportunidad() {
        return oportunidad;
    }

    public void setOportunidad(Oportunidad oportunidad) {
        this.oportunidad = oportunidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(LocalDateTime fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Override
    public String toString() {
        return "Inscripcion{" +
                "id=" + id +
                ", aspirante=" + aspirante +
                ", oportunidad=" + oportunidad +
                ", estado='" + estado + '\'' +
                ", fechaInscripcion=" + fechaInscripcion +
                ", observaciones='" + observaciones + '\'' +
                '}';
    }
}
