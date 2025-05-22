package com.example.demo.controller;
import com.example.demo.entity.Patrocinio;
import com.example.demo.service.PatrocinioService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/patrocinios")
public class PatrocinioController {

    private final PatrocinioService patrocinioService;

    public PatrocinioController(PatrocinioService patrocinioService) {
        this.patrocinioService = patrocinioService;
    }

    @GetMapping
    public List<Patrocinio> getAllPatrocinios() {
        return patrocinioService.getAllPatrocinios();
    }

    @GetMapping("/{id}")
    public Patrocinio getPatrocinioById(@PathVariable Long id) {
        return patrocinioService.getPatrocinioById(id);
    }

    @PostMapping
    public Patrocinio createPatrocinio(@RequestBody Patrocinio patrocinio) {
        return patrocinioService.savePatrocinio(patrocinio);
    }

    @PutMapping("/{id}")
    public Patrocinio updatePatrocinio(@PathVariable Long id, @RequestBody Patrocinio patrocinio) {
        patrocinio.setId(id);
        return patrocinioService.savePatrocinio(patrocinio);
    }

    @DeleteMapping("/{id}")
    public void deletePatrocinio(@PathVariable Long id) {
        patrocinioService.deletePatrocinio(id);
    }

}
