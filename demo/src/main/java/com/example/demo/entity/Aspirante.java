package com.example.demo.entity;
import jakarta.persistence.*;

@Entity
public class Aspirante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nivelAcademico;
    private String areaInteres;

    public Long getId() {
        return id;
    }

    public String getNivelAcademico() {
        return nivelAcademico;
    }

    public String getAreaInteres() {
        return areaInteres;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNivelAcademico(String nivelAcademico) {
        this.nivelAcademico = nivelAcademico;
    }

    public void setAreaInteres(String areaInteres) {
        this.areaInteres = areaInteres;
    }

    public Aspirante () {
    }
    
    public Aspirante (String nivelAcademico, String areaInteres) {
        this.nivelAcademico = nivelAcademico;
        this.areaInteres = areaInteres;
    }

    @Override
    public String toString() {
        return "Aspirante{" +
        "id=" + id +
        ", nivelAcademico='" + nivelAcademico + '\'' +
        ", areaInteres='" + areaInteres + '\'' +
        '}';

    }

}
