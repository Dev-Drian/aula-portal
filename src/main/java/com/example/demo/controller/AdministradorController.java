package com.example.demo.controller;

import com.example.demo.entity.Administrador;
import com.example.demo.entity.Usuario;
import com.example.demo.entity.Oportunidad;
import com.example.demo.service.AdministradorService;
import com.example.demo.service.UsuarioService;
import com.example.demo.service.OportunidadService;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdministradorController {

  private final AdministradorService administradorService;
  private final UsuarioService usuarioService;
  private final OportunidadService oportunidadService;

  public AdministradorController(AdministradorService administradorService, UsuarioService usuarioService, OportunidadService oportunidadService) {
    this.administradorService = administradorService;
    this.usuarioService = usuarioService;
    this.oportunidadService = oportunidadService;
  }

  @PostMapping("/usuarios/{id}/actualizar")
  public String actualizarUsuario(@PathVariable Long id,
                                  @RequestParam String nombre,
                                  @RequestParam String email,
                                  @RequestParam String rol,
                                  @RequestParam(required = false) String telefono) {
    Usuario usuario = usuarioService.getUsuarioById(id);
    if (usuario != null) {
      usuario.setNombre(nombre);
      usuario.setEmail(email);
      usuario.setRol(rol);

      if (rol.equals("ADMIN") && telefono != null) {
        Administrador admin = administradorService.getAdministradorByUsuarioId(id);
        if (admin == null) {
          admin = new Administrador();
          admin.setTelefono(telefono);
          admin.setUsuario(usuario);
          administradorService.saveAdministrador(admin);
        } else {
          admin.setTelefono(telefono);
          administradorService.saveAdministrador(admin);
        }
      } else if (!rol.equals("ADMIN")) {
        Administrador admin = administradorService.getAdministradorByUsuarioId(id);
        if (admin != null) {
          administradorService.deleteAdministrador(admin.getId());
        }
      }

      usuarioService.saveUsuario(usuario);
    }
    return "redirect:/admin/usuarios";
  }

  @GetMapping("/usuarios/{id}/eliminar")
  public String eliminarUsuario(@PathVariable Long id) {
    usuarioService.deleteUsuario(id);
    return "redirect:/admin/usuarios";
  }

  @GetMapping("/oportunidades/{id}/aprobar")
  public String aprobarOportunidad(@PathVariable Long id) {
    oportunidadService.aprobarOportunidad(id);
    return "redirect:/admin/oportunidades";
  }

  @GetMapping("/oportunidades/{id}/rechazar")
  public String rechazarOportunidad(@PathVariable Long id) {
    oportunidadService.rechazarOportunidad(id);
    return "redirect:/admin/oportunidades";
  }
}

