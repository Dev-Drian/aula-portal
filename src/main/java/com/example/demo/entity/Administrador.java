package com.example.demo.entity;
import jakarta.persistence.*;

@Entity
public class Administrador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private String telefono;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Administrador() {
    }

    public Administrador(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Administrador{" +
                "id=" + id +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}
