package com.example.demo.controller;
import com.example.demo.entity.Administrador;
import com.example.demo.service.AdministradorService;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/administradores")
public class AdministradorController {

    private final AdministradorService administradorService;

    public AdministradorController (AdministradorService administradorService) {
        this.administradorService = administradorService;
    }

    @GetMapping
    public List<Administrador> getAllAdministradores() {
        return administradorService.getAllAdministradores();
    }

    @GetMapping("/{id}")
    public Administrador getAdministradorById(@PathVariable Long id) {
        return administradorService.getAdministradorById(id);
    }

    @PostMapping
    public Administrador createAdministrador(@RequestBody Administrador administrador) {
        return administradorService.saveAdministrador(administrador);
    }

    @PutMapping("/{id}")
    public Administrador updateAdministrador(@PathVariable Long id, @RequestBody Administrador administrador) {
        administrador.setId(id);
        return administradorService.saveAdministrador(administrador);
    }

    @DeleteMapping("/{id}")
    public void deleteAdministrador(@PathVariable Long id) {
        administradorService.deleteAdministrador(id);
    }

}

