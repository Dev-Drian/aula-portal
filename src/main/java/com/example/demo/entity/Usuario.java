package com.example.demo.entity;
import jakarta.persistence.*;

@Entity
public class Usuario {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String contrasena;
    private String rol; // ASPIRANTE, INSTITUTO, ADMIN

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getRol() {
        return rol;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Usuario() {
    }

    public Usuario(String nombre, String email, String contrasena, String rol) {
        this.nombre = nombre;
        this.email = email;
        this.contrasena = contrasena;
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Usuario{" +
        "id=" + id +
        ", nombre=" + nombre + '\'' +
        ", email=" + email + '\'' +
        ", contrasena=" + contrasena + '\'' +
        ", rol=" + rol + '\'' +
        '}';
    }
}
