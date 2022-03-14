package com.grupo6.app.repositorios;

import com.grupo6.app.entidades.Cliente;
import com.grupo6.app.entidades.Habitacion;
import com.grupo6.app.entidades.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    @Query("SELECT c FROM Cliente c WHERE c.persona.id = :p")
    Optional<Cliente> findByPersonaId (@Param("p") Integer idPersona);

}
