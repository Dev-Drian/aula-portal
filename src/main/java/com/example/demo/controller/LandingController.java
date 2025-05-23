package com.example.demo.controller;

import com.example.demo.entity.Oportunidad;
import com.example.demo.entity.Usuario;
import com.example.demo.service.OportunidadService;
import com.example.demo.service.AdministradorService;
import com.example.demo.service.UsuarioService;
import com.example.demo.service.AspiranteService;
import com.example.demo.service.InstitutoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("/")
public class LandingController {

    private final OportunidadService oportunidadService;
    private final AdministradorService administradorService;
    private final UsuarioService usuarioService;
    private final AspiranteService aspiranteService;
    private final InstitutoService institutoService;

    public LandingController(OportunidadService oportunidadService,
                           AdministradorService administradorService,
                           UsuarioService usuarioService,
                           AspiranteService aspiranteService,
                           InstitutoService institutoService) {
        this.oportunidadService = oportunidadService;
        this.administradorService = administradorService;
        this.usuarioService = usuarioService;
        this.aspiranteService = aspiranteService;
        this.institutoService = institutoService;
    }

    @GetMapping
    public String landing() {
        return "landing";
    }

    @GetMapping("/auth/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/auth/register")
    public String register() {
        return "auth/register";
    }

    @GetMapping("/admin/dashboard")
    public String adminDashboard(Model model) {
        // Obtener estadísticas básicas
        model.addAttribute("totalUsuarios", usuarioService.countTotalUsuarios());
        model.addAttribute("totalAspirantes", aspiranteService.countTotalAspirantes());
        model.addAttribute("totalInstitutos", institutoService.countTotalInstitutos());
        model.addAttribute("totalOportunidades", oportunidadService.countTotalOportunidades());
        model.addAttribute("oportunidadesPendientes", oportunidadService.countOportunidadesPendientes());
        return "admin/dashboard";
    }

    @GetMapping("/admin/usuarios")
    public String listarUsuarios(Model model) {
        try {
            List<Usuario> usuarios = usuarioService.getAllUsuarios();
            model.addAttribute("usuarios", usuarios);
            return "admin/usuarios";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error al cargar los usuarios: " + e.getMessage());
            return "error";
        }
    }

    @GetMapping("/admin/usuarios/{id}/editar")
    public String editarUsuario(@PathVariable Long id, Model model) {
        model.addAttribute("usuario", usuarioService.getUsuarioById(id));
        return "admin/editar-usuario";
    }

    @GetMapping("/admin/oportunidades")
    public String gestionOportunidades(Model model) {
        model.addAttribute("oportunidades", oportunidadService.getAllOportunidades());
        return "admin/oportunidades";
    }

    @GetMapping("/instituto/dashboard")
    public String institutoDashboard() {
        return "instituto/dashboard";
    }

    @GetMapping("/instituto/oportunidades")
    public String institutoOportunidades() {
        return "instituto/oportunidades";
    }

    @GetMapping("/instituto/oportunidades/nueva")
    public String institutoNuevaOportunidad() {
        return "instituto/nueva-oportunidad";
    }

    @GetMapping("/aspirante/dashboard")
    public String aspiranteDashboard() {
        return "aspirante/dashboard";
    }

    @GetMapping("/aspirante/oportunidades")
    public String aspiranteOportunidades() {
        return "aspirante/oportunidades";
    }

    @GetMapping("/aspirante/inscripciones")
    public String aspiranteInscripciones() {
        return "aspirante/inscripciones";
    }

    @GetMapping("/oportunidad/{id}")
    public String verOportunidad(@PathVariable Long id, Model model) {
        Oportunidad oportunidad = oportunidadService.getOportunidadById(id);
        if (oportunidad == null) {
            return "redirect:/";
        }
        model.addAttribute("oportunidad", oportunidad);
        return "oportunidad/detalle";
    }
}
