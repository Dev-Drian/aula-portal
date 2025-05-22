package com.example.demo.controller;
import com.example.demo.entity.Beca;
import com.example.demo.service.BecaService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/becas")
public class BecaController {

    private final BecaService becaService;

    public BecaController(BecaService becaService) {
        this.becaService = becaService;
    }

    @GetMapping
    public List<Beca> getAllBecas() {
        return becaService.getAllBecas();
    }

    @GetMapping("/{id}")
    public Beca getBecaById(@PathVariable Long id) {
        return becaService.getBecaById(id);
    }

    @PostMapping
    public Beca createBeca(@RequestBody Beca beca) {
        return becaService.saveBeca(beca);
    }

    @PutMapping("/{id}")
    public Beca updateBeca(@PathVariable Long id, @RequestBody Beca beca) {
        beca.setId(id);
        return becaService.saveBeca(beca);
    }

    @DeleteMapping("/{id}")
    public void deleteBeca(@PathVariable Long id) {
        becaService.deleteBeca(id);
    }

}
