package com.example.demo.repository;

import com.example.demo.entity.Aspirante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AspiranteRepository extends JpaRepository<Aspirante, Long> {

    List<Aspirante> findByNivelAcademico(String nivelAcademico);
    List<Aspirante> findByAreaInteres(String areaInteres);
    List<Aspirante> findByNivelAcademicoAndAreaInteres(String nivelAcademico, String areaInteres);
    List<Aspirante> findByNivelAcademicoContainingOrAreaInteresContaining(String nivelAcademico, String areaInteres);
    List<Aspirante> findAllByOrderByNivelAcademicoAsc();
}