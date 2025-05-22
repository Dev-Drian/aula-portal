package com.example.demo.controller;
import com.example.demo.entity.Curso;
import com.example.demo.service.CursoService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping
    public List<Curso> getAllCursos() {
        return cursoService.getAllCursos();
    }

    @GetMapping("/{id}")
    public Curso getCursoById(@PathVariable Long id) {
        return cursoService.getCursoById(id);
    }

    @PostMapping
    public Curso createCurso(@RequestBody Curso curso) {
        return cursoService.saveCurso(curso);
    }

    @PutMapping("/{id}")
    public Curso updateCurso(@PathVariable Long id, @RequestBody Curso curso) {
        curso.setId(id);
        return cursoService.saveCurso(curso);
    }

    @DeleteMapping("/{id}")
    public void deleteCurso(@PathVariable Long id) {
        cursoService.deleteCurso(id);
    }

}
