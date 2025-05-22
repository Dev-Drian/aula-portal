package com.example.demo.service;

import com.example.demo.entity.Aspirante;
import com.example.demo.repository.AspiranteRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AspiranteService {
    
    private final AspiranteRepository aspiranteRepository;

    public AspiranteService (AspiranteRepository aspiranteRepository) {
        this.aspiranteRepository = aspiranteRepository;
    }

    public List<Aspirante> getAllAspirantes() {
        return aspiranteRepository.findAll();
    }
    
    public Aspirante getAspiranteById (Long id) {
        return aspiranteRepository.findById(id).orElse(null);
    }
    

    public Aspirante saveAspirante(Aspirante aspirante) {
        return aspiranteRepository.save(aspirante);
    }

    public void deleteAspirante(Long id) {
        aspiranteRepository.deleteById(id);
    }

    public List<Aspirante> getAspirantesPorNivelAcademico(String nivelAcademico) {
        return aspiranteRepository.findByNivelAcademico(nivelAcademico);
    }

    public List<Aspirante> getAspirantesPorAreaInteres(String areaInteres) {
        return aspiranteRepository.findByAreaInteres(areaInteres);
    }

    public List<Aspirante> getAspirantesPorNivelAcademicoAndAreaInteres(String nivelAcademico, String areaInteres) {
        return aspiranteRepository.findByNivelAcademicoAndAreaInteres(nivelAcademico, areaInteres);
    }

    public List<Aspirante> getAspirantesPorNivelAcademicoContainingOrAreaInteresContaining(String nivelAcademico, String areaInteres) {
        return aspiranteRepository.findByNivelAcademicoContainingOrAreaInteresContaining(nivelAcademico, areaInteres);
    }

    public List<Aspirante> getAllAspirantesOrdenadosPorNivelAcademico() {
        return aspiranteRepository.findAllByOrderByNivelAcademicoAsc();
    }
}

