package com.example.demo.entity;
import jakarta.persistence.*;

@Entity
public class Beca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private float monto;
    private String criterios;


    public Long getId() {
        return id;
    }

    public float getMonto() {
        return monto;
    }

    public String getCriterios() {
        return criterios;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public void setCriterio(String criterios) {
        this.criterios = criterios;
    }

    public Beca () {
    }

    public Beca(float monto, String criterios){
        this.monto = monto;
        this.criterios = criterios;
    }

    @Override
    public String toString() {
        return "Beca{" +
        "id=" + id+
        ", monto='" + monto + '\'' +
        "criterios='" + criterios + '\'' +
        '}';
    }
}