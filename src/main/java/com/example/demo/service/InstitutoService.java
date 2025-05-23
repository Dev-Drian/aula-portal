package com.example.demo.service;

import com.example.demo.entity.Instituto;
import com.example.demo.entity.Usuario;
import com.example.demo.repository.InstitutoRepository;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InstitutoService {

    private final InstitutoRepository institutoRepository;
    private final UsuarioRepository usuarioRepository;

    public InstitutoService(InstitutoRepository institutoRepository, UsuarioRepository usuarioRepository) {
        this.institutoRepository = institutoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Instituto> getAllInstitutos() {
        return institutoRepository.findAll();
    }

    public Instituto getInstitutoById(Long id) {
        return institutoRepository.findById(id).orElse(null);
    }

    public Instituto getInstitutoByUserId(Long userId) {
        Usuario usuario = usuarioRepository.findById(userId).orElse(null);
        if (usuario == null) {
            return null;
        }
        return institutoRepository.findByUsuario(usuario);
    }

    @Transactional
    public Instituto saveInstituto(Instituto instituto) {
        return institutoRepository.save(instituto);
    }

    @Transactional
    public void deleteInstituto(Long id) {
        institutoRepository.deleteById(id);
    }
}
