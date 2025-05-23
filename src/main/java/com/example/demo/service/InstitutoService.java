package com.example.demo.service;

import com.example.demo.entity.Instituto;
import com.example.demo.model.Usuario;
import com.example.demo.repository.InstitutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InstitutoService {

    @Autowired
    private InstitutoRepository institutoRepository;

    @Autowired
    private UsuarioService usuarioService;

    public List<Instituto> getAllInstitutos() {
        return institutoRepository.findAll();
    }

    public Instituto getInstitutoById(Long id) {
        return institutoRepository.findById(id).orElse(null);
    }

    public Instituto saveInstituto(Instituto instituto) {
        return institutoRepository.save(instituto);
    }

    public void deleteInstituto(Long id) {
        institutoRepository.deleteById(id);
    }

    public Instituto getInstitutoActual() {
        Usuario usuarioActual = usuarioService.getUsuarioActual();
        if (usuarioActual == null || !"INSTITUTO".equals(usuarioActual.getRol())) {
            return null;
        }
        return institutoRepository.findByUsuario(usuarioActual);
    }

    public Instituto findByUsuario(Usuario usuario) {
        return institutoRepository.findByUsuario(usuario);
    }

    public Instituto createInstitutoForUsuario(Usuario usuario) {
        Instituto instituto = new Instituto();
        instituto.setUsuario(usuario);
        instituto.setNombre(usuario.getNombre());
        return institutoRepository.save(instituto);
    }
}
