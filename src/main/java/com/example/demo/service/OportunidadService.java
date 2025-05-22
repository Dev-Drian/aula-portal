package com.example.demo.service;

import com.example.demo.entity.Oportunidad;
import com.example.demo.repository.OportunidadRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OportunidadService {

    private OportunidadRepository oportunidadRepository;

    public OportunidadService(OportunidadRepository oportunidadRepository){
        this.oportunidadRepository = oportunidadRepository;
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

}
