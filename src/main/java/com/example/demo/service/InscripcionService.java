package com.example.demo.service;

import com.example.demo.entity.Inscripcion;
import com.example.demo.entity.Aspirante;
import com.example.demo.entity.Oportunidad;
import com.example.demo.repository.InscripcionRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InscripcionService {

    private final InscripcionRepository inscripcionRepository;

    public InscripcionService(InscripcionRepository inscripcionRepository) {
        this.inscripcionRepository = inscripcionRepository;
    }

    public List<Inscripcion> getAllInscripciones() {
        return inscripcionRepository.findAll();
    }

    public Inscripcion getInscripcionById(Long id) {
        return inscripcionRepository.findById(id).orElse(null);
    }

    public List<Inscripcion> getInscripcionesByAspirante(Aspirante aspirante) {
        return inscripcionRepository.findByAspirante(aspirante);
    }

    public List<Inscripcion> getInscripcionesByOportunidad(Oportunidad oportunidad) {
        return inscripcionRepository.findByOportunidad(oportunidad);
    }

    public Inscripcion getInscripcionByAspiranteAndOportunidad(Aspirante aspirante, Oportunidad oportunidad) {
        return inscripcionRepository.findByAspiranteAndOportunidad(aspirante, oportunidad);
    }

    public Inscripcion inscribirAspirante(Aspirante aspirante, Oportunidad oportunidad) {
        // Verificar si ya existe una inscripción
        Inscripcion existente = getInscripcionByAspiranteAndOportunidad(aspirante, oportunidad);
        if (existente != null) {
            return existente;
        }

        // Crear nueva inscripción
        Inscripcion inscripcion = new Inscripcion(aspirante, oportunidad);
        return inscripcionRepository.save(inscripcion);
    }

    public Inscripcion actualizarEstado(Long id, String nuevoEstado) {
        Inscripcion inscripcion = getInscripcionById(id);
        if (inscripcion != null) {
            inscripcion.setEstado(nuevoEstado);
            return inscripcionRepository.save(inscripcion);
        }
        return null;
    }

    public Inscripcion saveInscripcion(Inscripcion inscripcion) {
        return inscripcionRepository.save(inscripcion);
    }

    public void deleteInscripcion(Long id) {
        inscripcionRepository.deleteById(id);
    }
}
