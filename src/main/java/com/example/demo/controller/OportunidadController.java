package com.example.demo.controller;

import com.example.demo.entity.Oportunidad;
import com.example.demo.entity.Aspirante;
import com.example.demo.service.OportunidadService;
import com.example.demo.service.AspiranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/oportunidades")
@CrossOrigin(origins = "*")
public class OportunidadController {

    @Autowired
    private OportunidadService oportunidadService;

    @Autowired
    private AspiranteService aspiranteService;

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
}
