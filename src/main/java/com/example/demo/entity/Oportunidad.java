package com.example.demo.entity;
import jakarta.persistence.*;
import java.util.List;


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
    
    @ManyToMany
    @JoinTable(
        name = "oportunidad_instituto",
        joinColumns = @JoinColumn(name = "oportunidad_id"),
        inverseJoinColumns = @JoinColumn(name = "instituto_id")
    )
    private List<Instituto> institutos;
    
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
    
    public List<Instituto> getInstitutos() {
        return institutos;
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
    
    public void setInstitutos(List<Instituto> institutos) {
        this.institutos = institutos;
    }
    
    public Oportunidad() {
    }
    
    public Oportunidad(String titulo, String descripcion, String tipo, String area, String estado, String shippingId, List<Instituto> institutos) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.area = area;
        this.estado = estado;
        this.shippingId = shippingId;
        this.institutos = institutos;
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
                ", institutos=" + institutos +
                '}';
    }
}