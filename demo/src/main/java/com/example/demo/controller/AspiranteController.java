package com.example.demo.controller;
import com.example.demo.entity.Aspirante;
import com.example.demo.service.AspiranteService;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aspirante")
public class AspiranteController {

    private final AspiranteService aspiranteService;

    public AspiranteController (AspiranteService aspiranteService) {
        this.aspiranteService = aspiranteService;
    }

    @GetMapping
    public List<Aspirante> getAllAspirantes() {
        return aspiranteService.getAllAspirantes();
    }

    @GetMapping("/{id}")
    public Aspirante getAspiranteById(@PathVariable Long id) {
        return aspiranteService.getAspiranteById(id);
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

}
