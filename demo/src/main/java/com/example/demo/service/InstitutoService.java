package com.example.demo.service;

import com.example.demo.entity.Instituto;
import com.example.demo.repository.InstitutoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InstitutoService {

    private InstitutoRepository institutoRepository;

    public InstitutoService(InstitutoRepository institutoRepository){
        this.institutoRepository = institutoRepository;
    }

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

}
