package com.example.demo.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

@Entity
public class Instituto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombreInstitucion;
    private String sitioWeb;
    
    @OneToOne
    @JoinColumn(name = "user_id")
    private Usuario usuario;
    
    @OneToMany(mappedBy = "instituto")
    @JsonIgnore
    private List<Oportunidad> oportunidades;
    
    public Long getId() {
        return id;
    }
    
    public String getNombreInstitucion() {
        return nombreInstitucion;
    }
    
    public String getSitioWeb() {
        return sitioWeb;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }
    
    public List<Oportunidad> getOportunidades() {
        return oportunidades;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public void setNombreInstitucion(String nombreInstitucion) {
        this.nombreInstitucion = nombreInstitucion;
    }
    
    public void setSitioWeb(String sitioWeb) {
        this.sitioWeb = sitioWeb;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public void setOportunidades(List<Oportunidad> oportunidades) {
        this.oportunidades = oportunidades;
    }
    
    public Instituto() {
    }
    
    public Instituto(String nombreInstituto, String sitioWeb) {
        this.nombreInstitucion = nombreInstituto;
        this.sitioWeb = sitioWeb;
    }
    
    @Override
    public String toString() {
        return "Instituto{" +
                "id=" + id +
                ", nombreInstituto='" + nombreInstitucion + '\'' +
                ", sitioWeb='" + sitioWeb + '\'' +
                ", usuario=" + usuario +
                ", oportunidades=" + oportunidades +
                '}';
    }
}
