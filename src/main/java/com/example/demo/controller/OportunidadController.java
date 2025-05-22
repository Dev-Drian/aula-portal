package com.example.demo.controller;
import com.example.demo.entity.Oportunidad;
import com.example.demo.service.OportunidadService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/oportunidades")
public class OportunidadController {

    private final OportunidadService oportunidadService;

    public OportunidadController(OportunidadService oportunidadService) {
        this.oportunidadService = oportunidadService;
    }

    @GetMapping
    public List<Oportunidad> getAllOportunidades() {
        return oportunidadService.getAllOportunidades();
    }

    @GetMapping("/{id}")
    public Oportunidad getOportunidadById(@PathVariable Long id) {
        return oportunidadService.getOportunidadById(id);
    }

    @PostMapping
    public Oportunidad createOportunidad(@RequestBody Oportunidad oportunidad) {
        return oportunidadService.saveOportunidad(oportunidad);
    }

    @PutMapping("/{id}")
    public Oportunidad updateOportunidad(@PathVariable Long id, @RequestBody Oportunidad oportunidad) {
        oportunidad.setId(id);
        return oportunidadService.saveOportunidad(oportunidad);
    }

    @DeleteMapping("/{id}")
    public void deleteOportunidad(@PathVariable Long id) {
        oportunidadService.deleteOportunidad(id);
    }

}
