package com.example.demo.entity;
import jakarta.persistence.*;

@Entity
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int duracion;
    private String modalidad;

    public Long getId() {
        return id;
    }

    public int getDuracion() {
        return duracion;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public Curso() {
    }

    public Curso(int duracion, String modalidad){
        this.duracion = duracion;
        this.modalidad = modalidad;
    }

    @Override
    public String toString() {
        return "Cursos{" +
        "id=" + id +
        ", duracion='" + duracion + '\'' +
        ", modalidad='" + modalidad + '\'' +
        '}';
    }
}
