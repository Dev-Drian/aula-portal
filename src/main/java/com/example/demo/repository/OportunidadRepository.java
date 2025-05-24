package com.example.demo.repository;

import com.example.demo.entity.Oportunidad;
import com.example.demo.entity.Instituto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OportunidadRepository extends JpaRepository<Oportunidad, Long> {
    List<Oportunidad> findByInstituto(Instituto instituto);
    List<Oportunidad> findByInstitutoId(Long institutoId);
    long countByEstado(String estado);
}
