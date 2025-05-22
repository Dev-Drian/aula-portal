package com.example.demo.entity;
import jakarta.persistence.*;

@Entity
public class Administrador {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Administrador () {
    }

    public Administrador (Long id) {
        this.id = id;
    }

    @Override
    public String toString () {
        return "Administrador{" +
        "id=" + id +
        '}';
    }

}
