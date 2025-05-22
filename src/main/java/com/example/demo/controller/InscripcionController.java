package com.example.demo.controller;
import com.example.demo.entity.Inscripcion;
import com.example.demo.entity.Aspirante;
import com.example.demo.entity.Oportunidad;
import com.example.demo.service.InscripcionService;
import com.example.demo.service.AspiranteService;
import com.example.demo.service.OportunidadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/inscripciones")
public class InscripcionController {

    private final InscripcionService inscripcionService;
    private final AspiranteService aspiranteService;
    private final OportunidadService oportunidadService;

    public InscripcionController(
        InscripcionService inscripcionService,
        AspiranteService aspiranteService,
        OportunidadService oportunidadService
    ) {
        this.inscripcionService = inscripcionService;
        this.aspiranteService = aspiranteService;
        this.oportunidadService = oportunidadService;
    }
    
    @GetMapping
    public List<Inscripcion> getAllInscripciones() {
        return inscripcionService.getAllInscripciones();
    }

    @GetMapping("/{id}")
    public Inscripcion getInscripcionById(@PathVariable Long id) {
        return inscripcionService.getInscripcionById(id);
    }

    @GetMapping("/aspirante/{aspiranteId}")
    public List<Inscripcion> getInscripcionesByAspirante(@PathVariable Long aspiranteId) {
        Aspirante aspirante = aspiranteService.getAspiranteById(aspiranteId);
        return aspirante != null ? inscripcionService.getInscripcionesByAspirante(aspirante) : List.of();
    }

    @GetMapping("/oportunidad/{oportunidadId}")
    public List<Inscripcion> getInscripcionesByOportunidad(@PathVariable Long oportunidadId) {
        Oportunidad oportunidad = oportunidadService.getOportunidadById(oportunidadId);
        return oportunidad != null ? inscripcionService.getInscripcionesByOportunidad(oportunidad) : List.of();
    }

    @PostMapping("/aspirante/{aspiranteId}/oportunidad/{oportunidadId}")
    public ResponseEntity<?> inscribirAspirante(
        @PathVariable Long aspiranteId,
        @PathVariable Long oportunidadId
    ) {
        Aspirante aspirante = aspiranteService.getAspiranteById(aspiranteId);
        Oportunidad oportunidad = oportunidadService.getOportunidadById(oportunidadId);
        
        if (aspirante == null || oportunidad == null) {
            return ResponseEntity.notFound().build();
        }

        Inscripcion inscripcion = inscripcionService.inscribirAspirante(aspirante, oportunidad);
        return ResponseEntity.ok(inscripcion);
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<?> actualizarEstado(
        @PathVariable Long id,
        @RequestParam String nuevoEstado
    ) {
        Inscripcion inscripcion = inscripcionService.actualizarEstado(id, nuevoEstado);
        return inscripcion != null ? ResponseEntity.ok(inscripcion) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public void deleteInscripcion(@PathVariable Long id) {
        inscripcionService.deleteInscripcion(id);
    }
}
