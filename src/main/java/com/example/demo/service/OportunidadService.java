package com.example.demo.service;

import com.example.demo.entity.Oportunidad;
import com.example.demo.entity.Instituto;
import com.example.demo.entity.Aspirante;
import com.example.demo.entity.Inscripcion;
import com.example.demo.repository.OportunidadRepository;
import com.example.demo.repository.InscripcionRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;

@Service
public class OportunidadService {

    private final OportunidadRepository oportunidadRepository;
    private final InstitutoService institutoService;
    private final InscripcionRepository inscripcionRepository;

    public OportunidadService(OportunidadRepository oportunidadRepository, 
                            InstitutoService institutoService,
                            InscripcionRepository inscripcionRepository) {
        this.oportunidadRepository = oportunidadRepository;
        this.institutoService = institutoService;
        this.inscripcionRepository = inscripcionRepository;
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

    public List<Map<String, Object>> getOportunidadesDisponiblesParaAspirante(Aspirante aspirante) {
        // Obtener todas las oportunidades aprobadas
        List<Oportunidad> oportunidadesAprobadas = oportunidadRepository.findAll().stream()
            .filter(oportunidad -> "APROBADA".equals(oportunidad.getEstado()))
            .collect(Collectors.toList());

        // Obtener las inscripciones del aspirante
        List<Inscripcion> inscripcionesAspirante = inscripcionRepository.findByAspirante(aspirante);
        Set<Long> oportunidadesInscritas = inscripcionesAspirante.stream()
            .map(inscripcion -> inscripcion.getOportunidad().getId())
            .collect(Collectors.toSet());

        // Filtrar y transformar las oportunidades
        return oportunidadesAprobadas.stream()
            .filter(oportunidad -> !oportunidadesInscritas.contains(oportunidad.getId()))
            .map(oportunidad -> {
                Map<String, Object> oportunidadMap = new HashMap<>();
                oportunidadMap.put("id", oportunidad.getId());
                oportunidadMap.put("titulo", oportunidad.getTitulo());
                oportunidadMap.put("descripcion", oportunidad.getDescripcion());
                oportunidadMap.put("tipo", oportunidad.getTipo());
                oportunidadMap.put("area", oportunidad.getArea());
                oportunidadMap.put("estado", oportunidad.getEstado());
                oportunidadMap.put("fecha", oportunidad.getFecha());
                oportunidadMap.put("fechaLimite", oportunidad.getFechaLimite());
                oportunidadMap.put("requisitos", oportunidad.getRequisitos());
                oportunidadMap.put("monto", oportunidad.getMonto());
                oportunidadMap.put("contacto", oportunidad.getContacto());
                oportunidadMap.put("instituto", oportunidad.getInstituto() != null ? 
                    oportunidad.getInstituto().getNombreInstitucion() : "No especificado");
                return oportunidadMap;
            })
            .collect(Collectors.toList());
    }

    public void aprobarOportunidad(Long id) {
        Oportunidad oportunidad = getOportunidadById(id);
        if (oportunidad != null) {
            oportunidad.setEstado("APROBADA");
            saveOportunidad(oportunidad);
        }
    }

    public void rechazarOportunidad(Long id) {
        Oportunidad oportunidad = getOportunidadById(id);
        if (oportunidad != null) {
            oportunidad.setEstado("RECHAZADA");
            saveOportunidad(oportunidad);
        }
    }

    public long countTotalOportunidades() {
        return oportunidadRepository.count();
    }

    public long countOportunidadesPendientes() {
        return oportunidadRepository.countByEstado("PENDIENTE");
    }
}
