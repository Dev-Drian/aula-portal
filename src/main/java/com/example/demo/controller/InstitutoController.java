package com.example.demo.controller;

import com.example.demo.entity.Instituto;
import com.example.demo.entity.Oportunidad;
import com.example.demo.service.InstitutoService;
import com.example.demo.service.OportunidadService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/instituto")
public class InstitutoController {

    private final InstitutoService institutoService;
    private final OportunidadService oportunidadService;

    public InstitutoController(InstitutoService institutoService, OportunidadService oportunidadService) {
        this.institutoService = institutoService;
        this.oportunidadService = oportunidadService;
    }

    

    @GetMapping("/perfil")
    @ResponseBody
    public ResponseEntity<Instituto> getPerfil(@RequestParam Long userId) {
        Instituto instituto = institutoService.getInstitutoByUserId(userId);
        return instituto != null ? ResponseEntity.ok(instituto) : ResponseEntity.notFound().build();
    }

    @PutMapping("/perfil")
    @ResponseBody
    public ResponseEntity<Instituto> actualizarPerfil(@RequestBody Instituto instituto) {
        return ResponseEntity.ok(institutoService.saveInstituto(instituto));
    }

    @GetMapping("/oportunidades")
    @ResponseBody
    public ResponseEntity<List<Oportunidad>> getOportunidades(@RequestParam Long institutoId) {
        return ResponseEntity.ok(oportunidadService.getOportunidadesByInstituto(institutoId));
    }

    @PostMapping("/oportunidades")
    @ResponseBody
    public ResponseEntity<Oportunidad> crearOportunidad(@RequestBody Oportunidad oportunidad) {
        oportunidad.setEstado("PENDIENTE");
        return ResponseEntity.ok(oportunidadService.saveOportunidad(oportunidad));
    }

    @PutMapping("/oportunidades/{id}")
    @ResponseBody
    public ResponseEntity<Oportunidad> actualizarOportunidad(@PathVariable Long id, @RequestBody Oportunidad oportunidad) {
        oportunidad.setId(id);
        return ResponseEntity.ok(oportunidadService.saveOportunidad(oportunidad));
    }

    @DeleteMapping("/oportunidades/{id}")
    @ResponseBody
    public ResponseEntity<Void> eliminarOportunidad(@PathVariable Long id) {
        oportunidadService.deleteOportunidad(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/oportunidades/{id}/estado")
    @ResponseBody
    public ResponseEntity<Oportunidad> actualizarEstado(@PathVariable Long id, @RequestBody Map<String, String> body) {
        String nuevoEstado = body.get("estado");
        Oportunidad oportunidad = oportunidadService.getOportunidadById(id);
        if (oportunidad != null) {
            oportunidad.setEstado(nuevoEstado);
            return ResponseEntity.ok(oportunidadService.saveOportunidad(oportunidad));
        }
        return ResponseEntity.notFound().build();
    }
}
