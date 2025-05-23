package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LandingController {

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
} 