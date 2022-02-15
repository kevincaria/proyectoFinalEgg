package com.grupo6.app.repositorios;

import com.grupo6.app.entidades.Nivel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface NivelRepository extends JpaRepository<Nivel, Integer> {
    Optional<Nivel> findByNombre(String nombre);
}