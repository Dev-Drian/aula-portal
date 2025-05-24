package com.example.demo.controller;
import com.example.demo.entity.Aspirante;
import com.example.demo.entity.Usuario;
import com.example.demo.entity.Oportunidad;
import com.example.demo.entity.OportunidadGuardada;
import com.example.demo.service.AspiranteService;
import com.example.demo.service.UsuarioService;
import com.example.demo.service.OportunidadService;
import com.example.demo.service.OportunidadGuardadaService;
import com.example.demo.service.InscripcionService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/aspirante")
public class AspiranteController {

    private final AspiranteService aspiranteService;
    private final UsuarioService usuarioService;
    private final OportunidadService oportunidadService;
    private final OportunidadGuardadaService oportunidadGuardadaService;
    private final InscripcionService inscripcionService;

    public AspiranteController(
        AspiranteService aspiranteService, 
        UsuarioService usuarioService,
        OportunidadService oportunidadService,
        OportunidadGuardadaService oportunidadGuardadaService,
        InscripcionService inscripcionService
    ) {
        this.aspiranteService = aspiranteService;
        this.usuarioService = usuarioService;
        this.oportunidadService = oportunidadService;
        this.oportunidadGuardadaService = oportunidadGuardadaService;
        this.inscripcionService = inscripcionService;
    }

    @GetMapping
    public List<Aspirante> getAllAspirantes() {
        return aspiranteService.getAllAspirantes();
    }

    @GetMapping("/{id}")
    public Aspirante getAspiranteById(@PathVariable Long id) {
        return aspiranteService.getAspiranteById(id);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<Aspirante> getAspiranteByUsuario(@PathVariable Long usuarioId) {
        Usuario usuario = usuarioService.getUsuarioById(usuarioId);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        Aspirante aspirante = aspiranteService.getAspiranteByUsuario(usuario);
        return aspirante != null ? ResponseEntity.ok(aspirante) : ResponseEntity.notFound().build();
    }

    @PostMapping("/usuario/{usuarioId}")
    public ResponseEntity<Aspirante> createOrGetAspirante(@PathVariable Long usuarioId) {
        Usuario usuario = usuarioService.getUsuarioById(usuarioId);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        Aspirante aspirante = aspiranteService.createOrGetAspirante(usuario);
        return aspirante != null ? ResponseEntity.ok(aspirante) : ResponseEntity.badRequest().build();
    }

    @PostMapping
    public Aspirante createAspirante(@RequestBody Aspirante aspirante) {
        return aspiranteService.saveAspirante(aspirante);
    }

    @PutMapping("/{id}")
    public Aspirante updateAspirante(@PathVariable Long id, @RequestBody Aspirante aspirante) {
        aspirante.setId(id);
        return aspiranteService.saveAspirante(aspirante);
    }

    @DeleteMapping("/{id}")
    public void deleteAspirante(@PathVariable Long id) {
        aspiranteService.deleteAspirante(id);
    }

    @GetMapping("/nivel/{nivel}")
    public List<Aspirante> getAspirantesPorNivelAcademico(@PathVariable String nivel) {
        return aspiranteService.getAspirantesPorNivelAcademico(nivel);
    }

    @GetMapping("/area/{area}")
    public List<Aspirante> getAspirantesPorAreaInteres(@PathVariable String area) {
        return aspiranteService.getAspirantesPorAreaInteres(area);
    }

    @GetMapping("/nivel/{nivel}/area/{area}")
    public List<Aspirante> getAspirantesPorNivelAcademicoYAreaInteres(@PathVariable String nivel, @PathVariable String area) {
        return aspiranteService.getAspirantesPorNivelAcademicoAndAreaInteres(nivel, area);
    }

    @GetMapping("/buscar")
    public List<Aspirante> buscarAspirantes(@RequestParam(required = false) String nivel, @RequestParam(required = false) String area) {
        return aspiranteService.getAspirantesPorNivelAcademicoContainingOrAreaInteresContaining(nivel, area);
    }

    @GetMapping("/ordenados")
    public List<Aspirante> getAspirantesOrdenadosPorNivelAcademico() {
        return aspiranteService.getAllAspirantesOrdenadosPorNivelAcademico();
    }

    @GetMapping("/perfil")
    public ResponseEntity<Aspirante> getPerfil(@RequestHeader("X-User-Id") Long usuarioId) {
        Usuario usuario = usuarioService.getUsuarioById(usuarioId);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        Aspirante aspirante = aspiranteService.createOrGetAspirante(usuario);
        return aspirante != null ? ResponseEntity.ok(aspirante) : ResponseEntity.badRequest().build();
    }

    @PutMapping("/perfil")
    public ResponseEntity<Aspirante> updatePerfil(
        @RequestHeader("X-User-Id") Long usuarioId,
        @RequestBody Map<String, String> perfilData
    ) {
        Usuario usuario = usuarioService.getUsuarioById(usuarioId);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        Aspirante aspirante = aspiranteService.createOrGetAspirante(usuario);
        if (aspirante == null) {
            return ResponseEntity.badRequest().build();
        }
        
        if (perfilData.containsKey("nivelAcademico")) {
            aspirante.setNivelAcademico(perfilData.get("nivelAcademico"));
        }
        if (perfilData.containsKey("areaInteres")) {
            aspirante.setAreaInteres(perfilData.get("areaInteres"));
        }
        
        Aspirante updatedAspirante = aspiranteService.saveAspirante(aspirante);
        return ResponseEntity.ok(updatedAspirante);
    }

    @GetMapping("/oportunidades/guardadas")
    public ResponseEntity<List<Oportunidad>> getOportunidadesGuardadas(@RequestParam Long aspiranteId) {
        Aspirante aspirante = aspiranteService.getAspiranteById(aspiranteId);
        if (aspirante == null) {
            return ResponseEntity.notFound().build();
        }
        List<OportunidadGuardada> guardadas = oportunidadGuardadaService.getOportunidadesGuardadasByAspirante(aspirante);
        List<Oportunidad> oportunidades = guardadas.stream()
            .map(OportunidadGuardada::getOportunidad)
            .collect(Collectors.toList());
        return ResponseEntity.ok(oportunidades);
    }

    @PostMapping("/oportunidades/{oportunidadId}/guardar")
    public ResponseEntity<?> guardarOportunidad(
        @PathVariable Long oportunidadId,
        @RequestParam Long aspiranteId
    ) {
        Aspirante aspirante = aspiranteService.getAspiranteById(aspiranteId);
        Oportunidad oportunidad = oportunidadService.getOportunidadById(oportunidadId);
        
        if (aspirante == null || oportunidad == null) {
            return ResponseEntity.notFound().build();
        }

        OportunidadGuardada guardada = oportunidadGuardadaService.guardarOportunidad(aspirante, oportunidad);
        return ResponseEntity.ok(guardada);
    }

    @DeleteMapping("/oportunidades/{oportunidadId}/guardar")
    public ResponseEntity<?> eliminarOportunidadGuardada(
        @PathVariable Long oportunidadId,
        @RequestParam Long aspiranteId
    ) {
        Aspirante aspirante = aspiranteService.getAspiranteById(aspiranteId);
        Oportunidad oportunidad = oportunidadService.getOportunidadById(oportunidadId);
        
        if (aspirante == null || oportunidad == null) {
            return ResponseEntity.notFound().build();
        }

        oportunidadGuardadaService.eliminarOportunidadGuardada(aspirante, oportunidad);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{aspiranteId}/estadisticas")
    public ResponseEntity<Map<String, Long>> getEstadisticas(@PathVariable Long aspiranteId) {
        Aspirante aspirante = aspiranteService.getAspiranteById(aspiranteId);
        if (aspirante == null) {
            return ResponseEntity.notFound().build();
        }

        Map<String, Long> estadisticas = Map.of(
            "totalInscripciones", inscripcionService.countInscripcionesByAspirante(aspirante),
            "totalAprobadas", inscripcionService.countInscripcionesByAspiranteAndEstado(aspirante, "APROBADA"),
            "totalPendientes", inscripcionService.countInscripcionesByAspiranteAndEstado(aspirante, "PENDIENTE"),
            "totalGuardadas", oportunidadGuardadaService.countOportunidadesGuardadasByAspirante(aspirante)
        );

        return ResponseEntity.ok(estadisticas);
    }
}
