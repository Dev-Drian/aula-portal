package com.example.demo.repository;

import com.example.demo.entity.OportunidadGuardada;
import com.example.demo.entity.Aspirante;
import com.example.demo.entity.Oportunidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OportunidadGuardadaRepository extends JpaRepository<OportunidadGuardada, Long> {
    List<OportunidadGuardada> findByAspirante(Aspirante aspirante);
    OportunidadGuardada findByAspiranteAndOportunidad(Aspirante aspirante, Oportunidad oportunidad);
    void deleteByAspiranteAndOportunidad(Aspirante aspirante, Oportunidad oportunidad);
    Long countByAspirante(Aspirante aspirante);
} 