package com.example.demo.entity;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Inscripcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(
        name = "inscripcion_aspirante" ,
        joinColumns = @JoinColumn(name = "inscripcion_id"),
        inverseJoinColumns = @JoinColumn(name = "aspirante_id")
    )
    
    private List<Aspirante> aspirantes;
    private String estado;

    public Long getId() {
        return id;
    }

    public List<Aspirante> gAspirantes() {
        return aspirantes;
    }

    public String getEstado() {
        return estado;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAspirante (List<Aspirante> aspirantes){
        this.aspirantes = aspirantes;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Inscripcion() {
    }

    public Inscripcion (List<Aspirante> aspirantes, String estado){
        this.aspirantes = aspirantes;
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Inscripcion{" +
        "id=" + id +
        ", aspirantes=" + aspirantes +
        ", estado='" + estado + '\'' +
        '}';
    }

    }
