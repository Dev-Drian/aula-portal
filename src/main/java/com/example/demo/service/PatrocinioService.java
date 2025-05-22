package com.example.demo.service;

import com.example.demo.entity.Patrocinio;
import com.example.demo.repository.PatrocinioRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PatrocinioService {

    private PatrocinioRepository patrocinioRepository;

    public PatrocinioService(PatrocinioRepository patrocinioRepository){
        this.patrocinioRepository = patrocinioRepository;
    }

    public List<Patrocinio> getAllPatrocinios() {
        return patrocinioRepository.findAll();
    }

    public Patrocinio getPatrocinioById(Long id) {
        return patrocinioRepository.findById(id).orElse(null);
    }

    public Patrocinio savePatrocinio(Patrocinio patrocinio) {
        return patrocinioRepository.save(patrocinio);
    }

    public void deletePatrocinio(Long id) {
        patrocinioRepository.deleteById(id);
    }

}
