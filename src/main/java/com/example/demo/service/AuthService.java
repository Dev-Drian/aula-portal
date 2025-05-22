package com.example.demo.service;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.LoginResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UsuarioRepository usuarioRepository;

    public AuthService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
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

        // Retornar respuesta
        return new LoginResponse(
            usuario.getId(),
            usuario.getNombre(),
            usuario.getEmail(),
            usuario.getRol()
        );
    }
} 