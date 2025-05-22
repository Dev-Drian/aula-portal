package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class OportunidadGuardada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "aspirante_id")
    private Aspirante aspirante;

    @ManyToOne
    @JoinColumn(name = "oportunidad_id")
    private Oportunidad oportunidad;

    private LocalDateTime fechaGuardado;

    public OportunidadGuardada() {
        this.fechaGuardado = LocalDateTime.now();
    }

    public OportunidadGuardada(Aspirante aspirante, Oportunidad oportunidad) {
        this.aspirante = aspirante;
        this.oportunidad = oportunidad;
        this.fechaGuardado = LocalDateTime.now();
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

    public LocalDateTime getFechaGuardado() {
        return fechaGuardado;
    }

    public void setFechaGuardado(LocalDateTime fechaGuardado) {
        this.fechaGuardado = fechaGuardado;
    }
} 