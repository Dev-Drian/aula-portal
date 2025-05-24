package com.example.demo.controller;

import com.example.demo.entity.Administrador;
import com.example.demo.entity.Usuario;
import com.example.demo.service.AdministradorService;
import com.example.demo.service.UsuarioService;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdministradorController {

  private final AdministradorService administradorService;
  private final UsuarioService usuarioService;

  public AdministradorController(AdministradorService administradorService, UsuarioService usuarioService) {
    this.administradorService = administradorService;
    this.usuarioService = usuarioService;
  }

  @GetMapping("/usuarios/{id}/editar")
  public String editarUsuario(@PathVariable Long id, Model model) {
    model.addAttribute("usuario", usuarioService.getUsuarioById(id));
    return "admin/editar-usuario";
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

      // Actualizar teléfono si es administrador
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
        // Si el usuario ya no es administrador, eliminar el registro de administrador
        Administrador admin = administradorService.getAdministradorByUsuarioId(id);
        if (admin != null) {
          administradorService.deleteAdministrador(admin.getId());
        }
      }

      usuarioService.saveUsuario(usuario);
    }
    return "redirect:/admin/usuarios";
  }

  @GetMapping("/usuarios")
  public String listarUsuarios(Model model) {
    model.addAttribute("usuarios", usuarioService.getAllUsuarios());
    return "admin/usuarios";
  }

  @GetMapping("/usuarios/{id}/eliminar")
  public String eliminarUsuario(@PathVariable Long id) {
    usuarioService.deleteUsuario(id);
    return "redirect:/admin/usuarios";
  }
}

