package com.example.demo.entity;
import jakarta.persistence.*;

@Entity
public class Patrocinio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipoApoyo;
    private String condiciones;


    public Long getId() {
        return id;
    }

    public String getTipoApoyo() {
        return tipoApoyo;
    }

    public String getCondiciones() {
        return condiciones;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTipoApoyo(String tipoApoyo) {
        this.tipoApoyo = tipoApoyo;
    }

    public void setCondiciones(String condiciones) {
        this.condiciones = condiciones;
    }

    public Patrocinio() {
    }

    public Patrocinio (String tipoApoyo, String condiciones) {
        this.tipoApoyo = tipoApoyo;
        this.condiciones = condiciones;
    }

    @Override
    public String toString() {
        return "patrocinio{" +
        "id=" + id+
        ", tipoApoyo='" + tipoApoyo + '\'' +
        ", condiciones='" + condiciones + '\'' +
        '}';
    }
}
