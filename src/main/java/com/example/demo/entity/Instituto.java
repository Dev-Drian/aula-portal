package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Instituto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombreInstitucion;
    private String sitioWeb;
    
    @ManyToMany(mappedBy = "institutos")
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
                ", oportunidades=" + oportunidades +
                '}';
    }
}
