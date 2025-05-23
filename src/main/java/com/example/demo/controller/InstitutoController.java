package com.example.demo.controller;

import com.example.demo.entity.Instituto;
import com.example.demo.entity.Oportunidad;
import com.example.demo.entity.Usuario;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.service.InstitutoService;
import com.example.demo.service.OportunidadService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/instituto")
public class InstitutoController {

    private final InstitutoService institutoService;
    private final OportunidadService oportunidadService;
    private final UsuarioRepository usuarioRepository;

    public InstitutoController(InstitutoService institutoService, OportunidadService oportunidadService, UsuarioRepository usuarioRepository) {
        this.institutoService = institutoService;
        this.oportunidadService = oportunidadService;
        this.usuarioRepository = usuarioRepository;
    }

    

    @GetMapping("/perfil")
    @ResponseBody
    public ResponseEntity<Instituto> getPerfil(@RequestParam Long userId) {
        Usuario usuario = usuarioRepository.findById(userId).orElse(null);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        Instituto instituto = institutoService.createOrGetInstituto(usuario);
        return instituto != null ? ResponseEntity.ok(instituto) : ResponseEntity.notFound().build();
    }

    @PutMapping("/perfil")
    @ResponseBody
    public ResponseEntity<Instituto> actualizarPerfil(@RequestBody Instituto institutoActualizado, @RequestParam Long userId) {
        Usuario usuario = usuarioRepository.findById(userId).orElse(null);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        
        Instituto instituto = institutoService.createOrGetInstituto(usuario);
        if (instituto == null) {
            return ResponseEntity.notFound().build();
        }

        // Mantener el ID original
        institutoActualizado.setId(instituto.getId());
        // Mantener la relación con el usuario
        institutoActualizado.setUsuario(usuario);
        
        Instituto institutoGuardado = institutoService.saveInstituto(institutoActualizado);
        return ResponseEntity.ok(institutoGuardado);
    }

    @GetMapping("/oportunidades")
    @ResponseBody
    public ResponseEntity<List<Oportunidad>> getOportunidades(@RequestParam Long institutoId) {
        return ResponseEntity.ok(oportunidadService.getOportunidadesByInstituto(institutoId));
    }

    @PostMapping("/oportunidades")
    @ResponseBody
    public ResponseEntity<Oportunidad> crearOportunidad(@RequestBody Oportunidad oportunidad, @RequestParam Long userId) {
        // Obtener el instituto asociado al usuario
        Usuario usuario = usuarioRepository.findById(userId).orElse(null);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        
        Instituto instituto = institutoService.createOrGetInstituto(usuario);
        if (instituto == null) {
            return ResponseEntity.notFound().build();
        }

        // Asignar el instituto y la fecha a la oportunidad
        oportunidad.setInstituto(instituto);
        oportunidad.setFecha(LocalDateTime.now());
        oportunidad.setEstado("PENDIENTE");
        
        return ResponseEntity.ok(oportunidadService.saveOportunidad(oportunidad));
    }

    @PutMapping("/oportunidades/{id}")
    @ResponseBody
    public ResponseEntity<Oportunidad> actualizarOportunidad(@PathVariable Long id, @RequestBody Oportunidad oportunidadActualizada) {
        Oportunidad oportunidadExistente = oportunidadService.getOportunidadById(id);
        if (oportunidadExistente == null) {
            return ResponseEntity.notFound().build();
        }

        // Mantener el instituto, fecha y estado original
        oportunidadActualizada.setInstituto(oportunidadExistente.getInstituto());
        oportunidadActualizada.setFecha(oportunidadExistente.getFecha());
        oportunidadActualizada.setEstado(oportunidadExistente.getEstado());
        oportunidadActualizada.setId(id);
        
        return ResponseEntity.ok(oportunidadService.saveOportunidad(oportunidadActualizada));
    }

    @DeleteMapping("/oportunidades/{id}")
    @ResponseBody
    public ResponseEntity<Void> eliminarOportunidad(@PathVariable Long id) {
        oportunidadService.deleteOportunidad(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/oportunidades/{id}/estado")
    @ResponseBody
    public ResponseEntity<Oportunidad> actualizarEstado(@PathVariable Long id, @RequestBody Map<String, String> body) {
        String nuevoEstado = body.get("estado");
        Oportunidad oportunidad = oportunidadService.getOportunidadById(id);
        if (oportunidad != null) {
            oportunidad.setEstado(nuevoEstado);
            return ResponseEntity.ok(oportunidadService.saveOportunidad(oportunidad));
        }
        return ResponseEntity.notFound().build();
    }
}
