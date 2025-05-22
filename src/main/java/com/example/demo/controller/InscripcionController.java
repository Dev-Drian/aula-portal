package com.example.demo.controller;
import com.example.demo.entity.Inscripcion;
import com.example.demo.service.InscripcionService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/inscripciones")
public class InscripcionController {

    private final InscripcionService inscripcionService;

    public InscripcionController (InscripcionService inscripcionService) {
        this.inscripcionService = inscripcionService;
    }
    
    @GetMapping
    public List<Inscripcion> getAllInscripciones() {
        return inscripcionService.getAllInscripciones();
    }

    @GetMapping("/{id}")
    public Inscripcion getInscripcionById(@PathVariable Long id) {
        return inscripcionService.getInscripcionById(id);
    }

    @PostMapping
    public Inscripcion createInscripcion(@RequestBody Inscripcion inscripcion) {
        return inscripcionService.saveInscripcion(inscripcion);
    }

    @PutMapping("/{id}")
    public Inscripcion updateInscripcion(@PathVariable Long id, @RequestBody Inscripcion inscripcion) {
        inscripcion.setId(id);
        return inscripcionService.saveInscripcion(inscripcion);
    }

    @DeleteMapping("/{id}")
    public void deleteInscripcion(@PathVariable Long id) {
        inscripcionService.deleteInscripcion(id);
    }

}
