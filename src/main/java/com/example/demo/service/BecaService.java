package com.example.demo.service;

import com.example.demo.entity.Beca;
import com.example.demo.repository.BecaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BecaService {

    private final BecaRepository becaRepository;

    public BecaService(BecaRepository becaRepository){
        this.becaRepository = becaRepository;
    }

    public List<Beca> getAllBecas() {
        return becaRepository.findAll();
    }

    public Beca getBecaById(Long id) {
        return becaRepository.findById(id).orElse(null);
    }

    public Beca saveBeca(Beca beca) {
        return becaRepository.save(beca);
    }

    public void deleteBeca(Long id) {
        becaRepository.deleteById(id);
    }

}
