package com.example.demo.entity;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.time.LocalDate;

@Entity
public class Oportunidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String titulo;
    private String descripcion;
    private String tipo;
    private String area;
    private String estado;
    private String shippingId;
    private LocalDateTime fecha;
    private String requisitos;
    private String monto;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaLimite;
    
    private String contacto;
    
    @ManyToOne
    @JoinColumn(name = "instituto_id")
    @JsonIgnore
    private Instituto instituto;
    
    public Long getId() {
        return id;
    }
    
    public String getTitulo() {
        return titulo;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public String getTipo() {
        return tipo;
    }
    
    public String getArea() {
        return area;
    }
    
    public String getEstado() {
        return estado;
    }
    
    public String getShippingId() {
        return shippingId;
    }
    
    public LocalDateTime getFecha() {
        return fecha;
    }
    
    public Instituto getInstituto() {
        return instituto;
    }
    
    public String getRequisitos() {
        return requisitos;
    }
    
    public String getMonto() {
        return monto;
    }
    
    public LocalDate getFechaLimite() {
        return fechaLimite;
    }
    
    public String getContacto() {
        return contacto;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public void setArea(String area) {
        this.area = area;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public void setShippingId(String shippingId) {
        this.shippingId = shippingId;
    }
    
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
    
    public void setInstituto(Instituto instituto) {
        this.instituto = instituto;
    }
    
    public void setRequisitos(String requisitos) {
        this.requisitos = requisitos;
    }
    
    public void setMonto(String monto) {
        this.monto = monto;
    }
    
    public void setFechaLimite(LocalDate fechaLimite) {
        this.fechaLimite = fechaLimite;
    }
    
    public void setContacto(String contacto) {
        this.contacto = contacto;
    }
    
    public Oportunidad() {
    }
    
    public Oportunidad(String titulo, String descripcion, String tipo, String area, String estado, String shippingId, Instituto instituto) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.area = area;
        this.estado = estado;
        this.shippingId = shippingId;
        this.instituto = instituto;
    }
    
    @Override
    public String toString() {
        return "Oportunidad{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", tipo='" + tipo + '\'' +
                ", area='" + area + '\'' +
                ", estado='" + estado + '\'' +
                ", shippingId='" + shippingId + '\'' +
                ", instituto=" + instituto +
                '}';
    }
}