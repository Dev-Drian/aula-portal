package com.example.demo.service;

import com.example.demo.entity.OportunidadGuardada;
import com.example.demo.entity.Aspirante;
import com.example.demo.entity.Oportunidad;
import com.example.demo.repository.OportunidadGuardadaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class OportunidadGuardadaService {

    private final OportunidadGuardadaRepository oportunidadGuardadaRepository;

    public OportunidadGuardadaService(OportunidadGuardadaRepository oportunidadGuardadaRepository) {
        this.oportunidadGuardadaRepository = oportunidadGuardadaRepository;
    }

    public List<OportunidadGuardada> getOportunidadesGuardadasByAspirante(Aspirante aspirante) {
        return oportunidadGuardadaRepository.findByAspirante(aspirante);
    }

    @Transactional
    public OportunidadGuardada guardarOportunidad(Aspirante aspirante, Oportunidad oportunidad) {
        OportunidadGuardada guardada = new OportunidadGuardada();
        guardada.setAspirante(aspirante);
        guardada.setOportunidad(oportunidad);
        return oportunidadGuardadaRepository.save(guardada);
    }

    @Transactional
    public void eliminarOportunidadGuardada(Aspirante aspirante, Oportunidad oportunidad) {
        oportunidadGuardadaRepository.deleteByAspiranteAndOportunidad(aspirante, oportunidad);
    }

    public Long countOportunidadesGuardadasByAspirante(Aspirante aspirante) {
        return oportunidadGuardadaRepository.countByAspirante(aspirante);
    }
} 