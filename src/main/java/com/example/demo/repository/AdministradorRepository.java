package com.example.demo.repository;

import com.example.demo.entity.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Long> {
    Administrador findByUsuarioId(Long usuarioId);
}
