package com.example.demo.controller;
import com.example.demo.entity.Instituto;
import com.example.demo.service.InstitutoService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/institutos")
public class InstitutoController {

    private final InstitutoService institutoService;

    public InstitutoController(InstitutoService institutoService) {
        this.institutoService = institutoService;
    }

    @GetMapping
    public List<Instituto> getAllInstitutos() {
        return institutoService.getAllInstitutos();
    }

    @GetMapping("/{id}")
    public Instituto getInstitutoById(@PathVariable Long id) {
        return institutoService.getInstitutoById(id);
    }

    @PostMapping
    public Instituto createInstituto(@RequestBody Instituto instituto) {
        return institutoService.saveInstituto(instituto);
    }

    @PutMapping("/{id}")
    public Instituto updateInstituto(@PathVariable Long id, @RequestBody Instituto instituto) {
        instituto.setId(id);
        return institutoService.saveInstituto(instituto);
    }

    @DeleteMapping("/{id}")
    public void deleteInstituto(@PathVariable Long id) {
        institutoService.deleteInstituto(id);
    }

}
