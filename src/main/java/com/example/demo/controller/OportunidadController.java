package com.example.demo.controller;

import com.example.demo.entity.Oportunidad;
import com.example.demo.entity.Aspirante;
import com.example.demo.entity.Inscripcion;
import com.example.demo.service.OportunidadService;
import com.example.demo.service.AspiranteService;
import com.example.demo.service.InscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/oportunidades")
@CrossOrigin(origins = "*")
public class OportunidadController {

    @Autowired
    private OportunidadService oportunidadService;

    @Autowired
    private AspiranteService aspiranteService;

    @Autowired
    private InscripcionService inscripcionService;

    @GetMapping
    public List<Oportunidad> getAllOportunidades() {
        return oportunidadService.getAllOportunidades();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Oportunidad> getOportunidadById(@PathVariable Long id) {
        Oportunidad oportunidad = oportunidadService.getOportunidadById(id);
        if (oportunidad != null) {
            return ResponseEntity.ok(oportunidad);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public Oportunidad createOportunidad(@RequestBody Oportunidad oportunidad) {
        return oportunidadService.saveOportunidad(oportunidad);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Oportunidad> updateOportunidad(@PathVariable Long id, @RequestBody Oportunidad oportunidad) {
        Oportunidad existingOportunidad = oportunidadService.getOportunidadById(id);
        if (existingOportunidad != null) {
        oportunidad.setId(id);
            return ResponseEntity.ok(oportunidadService.saveOportunidad(oportunidad));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOportunidad(@PathVariable Long id) {
        Oportunidad oportunidad = oportunidadService.getOportunidadById(id);
        if (oportunidad != null) {
        oportunidadService.deleteOportunidad(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/instituto/{institutoId}")
    public List<Oportunidad> getOportunidadesByInstituto(@PathVariable Long institutoId) {
        return oportunidadService.getOportunidadesByInstituto(institutoId);
    }

    @GetMapping("/disponibles/{aspiranteId}")
    public ResponseEntity<List<Map<String, Object>>> getOportunidadesDisponibles(@PathVariable Long aspiranteId) {
        Aspirante aspirante = aspiranteService.getAspiranteById(aspiranteId);
        if (aspirante != null) {
            return ResponseEntity.ok(oportunidadService.getOportunidadesDisponiblesParaAspirante(aspirante));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/aspirantes")
    public ResponseEntity<List<Map<String, Object>>> getAspirantesByOportunidad(@PathVariable Long id) {
        Oportunidad oportunidad = oportunidadService.getOportunidadById(id);
        if (oportunidad != null) {
            List<Inscripcion> inscripciones = inscripcionService.getInscripcionesByOportunidad(oportunidad);
            List<Map<String, Object>> aspirantes = inscripciones.stream()
                .map(inscripcion -> {
                    Map<String, Object> aspiranteMap = new HashMap<>();
                    aspiranteMap.put("id", inscripcion.getAspirante().getId());
                    aspiranteMap.put("nombre", inscripcion.getAspirante().getUsuario().getNombre());
                    aspiranteMap.put("email", inscripcion.getAspirante().getUsuario().getEmail());
                    aspiranteMap.put("estado", inscripcion.getEstado());
                    aspiranteMap.put("fechaInscripcion", inscripcion.getFechaInscripcion());
                    return aspiranteMap;
                })
                .collect(Collectors.toList());
            return ResponseEntity.ok(aspirantes);
        }
        return ResponseEntity.notFound().build();
    }
}
