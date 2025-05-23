package com.example.demo.controller;

import com.example.demo.entity.Oportunidad;
import com.example.demo.service.OportunidadService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LandingController {

  private final OportunidadService oportunidadService;

  public LandingController(OportunidadService oportunidadService) {
    this.oportunidadService = oportunidadService;
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
    public String adminDashboard() {
        return "admin/dashboard";
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
