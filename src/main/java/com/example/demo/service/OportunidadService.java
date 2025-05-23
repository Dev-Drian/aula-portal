package com.example.demo.service;

import com.example.demo.entity.Oportunidad;
import com.example.demo.entity.Instituto;
import com.example.demo.repository.OportunidadRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OportunidadService {

    private final OportunidadRepository oportunidadRepository;
    private final InstitutoService institutoService;

    public OportunidadService(OportunidadRepository oportunidadRepository, InstitutoService institutoService){
        this.oportunidadRepository = oportunidadRepository;
        this.institutoService = institutoService;
    }

    public List<Oportunidad> getAllOportunidades() {
        return oportunidadRepository.findAll();
    }

    public Oportunidad getOportunidadById(Long id) {
        return oportunidadRepository.findById(id).orElse(null);
    }

    public Oportunidad saveOportunidad(Oportunidad oportunidad) {
        return oportunidadRepository.save(oportunidad);
    }

    public void deleteOportunidad(Long id) {
        oportunidadRepository.deleteById(id);
    }

    public List<Oportunidad> getOportunidadesByInstituto(Long institutoId) {
        Instituto instituto = institutoService.getInstitutoById(institutoId);
        if (instituto == null) {
            return List.of();
        }
        return oportunidadRepository.findByInstituto(instituto);
    }
}
