package com.example.demo.controller;
import com.example.demo.entity.Aspirante;
import com.example.demo.entity.Usuario;
import com.example.demo.service.AspiranteService;
import com.example.demo.service.UsuarioService;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/aspirante")
public class AspiranteController {

    private final AspiranteService aspiranteService;
    private final UsuarioService usuarioService;

    public AspiranteController(AspiranteService aspiranteService, UsuarioService usuarioService) {
        this.aspiranteService = aspiranteService;
        this.usuarioService = usuarioService;
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
    public ResponseEntity<Aspirante> getPerfil() {
        // TODO: Obtener el ID del usuario autenticado
        Long usuarioId = 1L; // Temporalmente hardcodeado
        Usuario usuario = usuarioService.getUsuarioById(usuarioId);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        Aspirante aspirante = aspiranteService.createOrGetAspirante(usuario);
        return aspirante != null ? ResponseEntity.ok(aspirante) : ResponseEntity.badRequest().build();
    }

    @PutMapping("/perfil")
    public ResponseEntity<Aspirante> updatePerfil(@RequestBody Map<String, String> perfilData) {
        // TODO: Obtener el ID del usuario autenticado
        Long usuarioId = 1L; // Temporalmente hardcodeado
        Usuario usuario = usuarioService.getUsuarioById(usuarioId);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        // Obtener o crear el aspirante
        Aspirante aspirante = aspiranteService.createOrGetAspirante(usuario);
        if (aspirante == null) {
            return ResponseEntity.badRequest().build();
        }
        
        // Actualizar los campos
        if (perfilData.containsKey("nivelAcademico")) {
            aspirante.setNivelAcademico(perfilData.get("nivelAcademico"));
        }
        if (perfilData.containsKey("areaInteres")) {
            aspirante.setAreaInteres(perfilData.get("areaInteres"));
        }
        
        // Guardar los cambios
        Aspirante updatedAspirante = aspiranteService.saveAspirante(aspirante);
        return ResponseEntity.ok(updatedAspirante);
    }
}
