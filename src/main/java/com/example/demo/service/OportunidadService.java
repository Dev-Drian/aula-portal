package com.example.demo.service;

import com.example.demo.entity.Instituto;
import com.example.demo.entity.Oportunidad;
import com.example.demo.repository.OportunidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OportunidadService {

    @Autowired
    private OportunidadRepository oportunidadRepository;

    public List<Oportunidad> findByInstituto(Instituto instituto) {
        return oportunidadRepository.findByInstituto(instituto);
    }

    public List<Oportunidad> findByInstitutoAndEstado(Instituto instituto, String estado) {
        return oportunidadRepository.findByInstitutoAndEstado(instituto, estado);
    }

    public Oportunidad findById(Long id) {
        return oportunidadRepository.findById(id).orElse(null);
    }

    @Transactional
    public Oportunidad save(Oportunidad oportunidad) {
        return oportunidadRepository.save(oportunidad);
    }

    @Transactional
    public void delete(Long id) {
        oportunidadRepository.deleteById(id);
    }

    public Long countByInstituto(Instituto instituto) {
        return oportunidadRepository.countByInstituto(instituto);
    }

    public Long countByInstitutoAndEstado(Instituto instituto, String estado) {
        return oportunidadRepository.countByInstitutoAndEstado(instituto, estado);
    }
}
