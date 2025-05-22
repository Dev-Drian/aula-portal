package com.example.demo.service;

import com.example.demo.entity.Administrador;
import com.example.demo.repository.AdministradorRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AdministradorService {

    private final AdministradorRepository administradorRepository;

    public AdministradorService(AdministradorRepository administradorRepository){
        this.administradorRepository = administradorRepository;
    }


    public List<Administrador> getAllAdministradores() {
        return administradorRepository.findAll();
    }

    public Administrador getAdministradorById(Long id) {
        return administradorRepository.findById(id).orElse(null);
    }
    
    public Administrador saveAdministrador(Administrador administrador) {
        return administradorRepository.save(administrador);
    }

    public void deleteAdministrador(Long id) {
        administradorRepository.deleteById(id);
    }
        
    }

