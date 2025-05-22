package com.example.demo.service;

import com.example.demo.entity.Inscripcion;
import com.example.demo.repository.InscripcionRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InscripcionService {

    private InscripcionRepository inscripcionRepository;

    public InscripcionService(InscripcionRepository inscripcionRepository) {
        this.inscripcionRepository = inscripcionRepository;
    }

    public List<Inscripcion> getAllInscripciones() {
        return inscripcionRepository.findAll();
    }

    public Inscripcion getInscripcionById(Long id) {
        return inscripcionRepository.findById(id).orElse(null);
    }

    public Inscripcion saveInscripcion(Inscripcion inscripcion) {
        return inscripcionRepository.save(inscripcion);
    }

    public void deleteInscripcion(Long id) {
        inscripcionRepository.deleteById(id);
    }

}
