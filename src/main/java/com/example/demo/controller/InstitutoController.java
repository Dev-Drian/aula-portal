package com.example.demo.controller;

import com.example.demo.entity.Instituto;
import com.example.demo.entity.Oportunidad;
import com.example.demo.service.InstitutoService;
import com.example.demo.service.OportunidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/instituto")
public class InstitutoController {

    @Autowired
    private InstitutoService institutoService;

    @Autowired
    private OportunidadService oportunidadService;

    @GetMapping("/estadisticas")
    public ResponseEntity<Map<String, Long>> getEstadisticas() {
        Instituto instituto = institutoService.getInstitutoActual();
        if (instituto == null) {
            return ResponseEntity.notFound().build();
        }

        Map<String, Long> estadisticas = new HashMap<>();
        estadisticas.put("totalOportunidades", oportunidadService.countByInstituto(instituto));
        estadisticas.put("totalPendientes", oportunidadService.countByInstitutoAndEstado(instituto, "PENDIENTE"));
        estadisticas.put("totalAprobadas", oportunidadService.countByInstitutoAndEstado(instituto, "APROBADA"));

        return ResponseEntity.ok(estadisticas);
    }

    @GetMapping("/oportunidades")
    public ResponseEntity<List<Oportunidad>> getOportunidades(@RequestParam(required = false) String status) {
        Instituto instituto = institutoService.getInstitutoActual();
        if (instituto == null) {
            return ResponseEntity.notFound().build();
        }

        List<Oportunidad> oportunidades;
        if (status != null && !status.isEmpty()) {
            oportunidades = oportunidadService.findByInstitutoAndEstado(instituto, status);
        } else {
            oportunidades = oportunidadService.findByInstituto(instituto);
        }

        return ResponseEntity.ok(oportunidades);
    }

    @GetMapping("/oportunidades/{id}")
    public ResponseEntity<Oportunidad> getOportunidad(@PathVariable Long id) {
        Instituto instituto = institutoService.getInstitutoActual();
        if (instituto == null) {
            return ResponseEntity.notFound().build();
        }

        Oportunidad oportunidad = oportunidadService.findById(id);
        if (oportunidad == null || !oportunidad.getInstituto().equals(instituto)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(oportunidad);
    }

    @PostMapping("/oportunidades")
    public ResponseEntity<Oportunidad> createOportunidad(@RequestBody Oportunidad oportunidad) {
        Instituto instituto = institutoService.getInstitutoActual();
        if (instituto == null) {
            return ResponseEntity.notFound().build();
        }

        oportunidad.setInstituto(instituto);
        oportunidad.setEstado("PENDIENTE");
        oportunidad.setActiva(false);

        Oportunidad savedOportunidad = oportunidadService.save(oportunidad);
        return ResponseEntity.ok(savedOportunidad);
    }

    @PutMapping("/oportunidades/{id}")
    public ResponseEntity<Oportunidad> updateOportunidad(@PathVariable Long id, @RequestBody Oportunidad oportunidad) {
        Instituto instituto = institutoService.getInstitutoActual();
        if (instituto == null) {
            return ResponseEntity.notFound().build();
        }

        Oportunidad existingOportunidad = oportunidadService.findById(id);
        if (existingOportunidad == null || !existingOportunidad.getInstituto().equals(instituto)) {
            return ResponseEntity.notFound().build();
        }

        if (!"PENDIENTE".equals(existingOportunidad.getEstado())) {
            return ResponseEntity.badRequest().build();
        }

        oportunidad.setId(id);
        oportunidad.setInstituto(instituto);
        oportunidad.setEstado("PENDIENTE");
        oportunidad.setActiva(false);

        Oportunidad updatedOportunidad = oportunidadService.save(oportunidad);
        return ResponseEntity.ok(updatedOportunidad);
    }

    @DeleteMapping("/oportunidades/{id}")
    public ResponseEntity<Void> deleteOportunidad(@PathVariable Long id) {
        Instituto instituto = institutoService.getInstitutoActual();
        if (instituto == null) {
            return ResponseEntity.notFound().build();
        }

        Oportunidad oportunidad = oportunidadService.findById(id);
        if (oportunidad == null || !oportunidad.getInstituto().equals(instituto)) {
            return ResponseEntity.notFound().build();
        }

        if (!"PENDIENTE".equals(oportunidad.getEstado())) {
            return ResponseEntity.badRequest().build();
        }

        oportunidadService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/oportunidades/{id}/toggle")
    public ResponseEntity<Oportunidad> toggleOportunidad(@PathVariable Long id, @RequestBody Map<String, Boolean> body) {
        Instituto instituto = institutoService.getInstitutoActual();
        if (instituto == null) {
            return ResponseEntity.notFound().build();
        }

        Oportunidad oportunidad = oportunidadService.findById(id);
        if (oportunidad == null || !oportunidad.getInstituto().equals(instituto)) {
            return ResponseEntity.notFound().build();
        }

        if (!"APROBADA".equals(oportunidad.getEstado())) {
            return ResponseEntity.badRequest().build();
        }

        oportunidad.setActiva(body.get("activa"));
        Oportunidad updatedOportunidad = oportunidadService.save(oportunidad);
        return ResponseEntity.ok(updatedOportunidad);
    }
}
