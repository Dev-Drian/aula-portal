package com.example.demo.repository;

import com.example.demo.entity.Instituto;
import com.example.demo.entity.Oportunidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OportunidadRepository extends JpaRepository<Oportunidad, Long> {
    List<Oportunidad> findByInstituto(Instituto instituto);
    List<Oportunidad> findByInstitutoAndEstado(Instituto instituto, String estado);
    Long countByInstituto(Instituto instituto);
    Long countByInstitutoAndEstado(Instituto instituto, String estado);
}
