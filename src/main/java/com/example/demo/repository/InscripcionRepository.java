package com.example.demo.repository;

import com.example.demo.entity.Inscripcion;
import com.example.demo.entity.Aspirante;
import com.example.demo.entity.Oportunidad;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {
    List<Inscripcion> findByAspirante(Aspirante aspirante);
    List<Inscripcion> findByOportunidad(Oportunidad oportunidad);
    Inscripcion findByAspiranteAndOportunidad(Aspirante aspirante, Oportunidad oportunidad);
}