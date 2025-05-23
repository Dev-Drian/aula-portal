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

    public OportunidadGuardada guardarOportunidad(Aspirante aspirante, Oportunidad oportunidad) {
        OportunidadGuardada existente = oportunidadGuardadaRepository.findByAspiranteAndOportunidad(aspirante, oportunidad);
        if (existente != null) {
            return existente;
        }
        OportunidadGuardada nueva = new OportunidadGuardada(aspirante, oportunidad);
        return oportunidadGuardadaRepository.save(nueva);
    }

    @Transactional
    public void eliminarOportunidadGuardada(Aspirante aspirante, Oportunidad oportunidad) {
        oportunidadGuardadaRepository.deleteByAspiranteAndOportunidad(aspirante, oportunidad);
    }
} 