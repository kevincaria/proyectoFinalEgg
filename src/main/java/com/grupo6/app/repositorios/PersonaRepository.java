package com.grupo6.app.repositorios;

import com.grupo6.app.entidades.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PersonaRepository extends JpaRepository<Persona, Integer> {
    Optional<Persona> findByNombre(String nombre);

    Optional<Persona> findByDni(String dni);


}
