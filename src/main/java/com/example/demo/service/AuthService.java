package com.example.demo.service;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.LoginResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.Usuario;
import com.example.demo.entity.Instituto;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.service.InstitutoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {
    private final UsuarioRepository usuarioRepository;
    private final InstitutoService institutoService;

    public AuthService(UsuarioRepository usuarioRepository, InstitutoService institutoService) {
        this.usuarioRepository = usuarioRepository;
        this.institutoService = institutoService;
    }

    public LoginResponse login(LoginRequest request) {
        Usuario usuario = usuarioRepository.findByEmailAndContrasena(request.getEmail(), request.getContrasena());
        if (usuario != null) {
            return new LoginResponse(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getRol()
            );
        }
        return null;
    }

    @Transactional
    public LoginResponse register(RegisterRequest request) {
        // Verificar si el email ya existe
        if (usuarioRepository.findByEmail(request.getEmail()) != null) {
            return null;
        }

        // Crear nuevo usuario
        Usuario usuario = new Usuario(
            request.getNombre(),
            request.getEmail(),
            request.getContrasena(),
            request.getRol()
        );

        // Guardar usuario
        usuario = usuarioRepository.save(usuario);

        // Si es un instituto, crear el perfil de instituto
        if ("INSTITUTO".equals(usuario.getRol())) {
            institutoService.createOrGetInstituto(usuario);
        }

        // Retornar respuesta
        return new LoginResponse(
            usuario.getId(),
            usuario.getNombre(),
            usuario.getEmail(),
            usuario.getRol()
        );
    }
} 