package com.grupo6.app.repositorios;

import com.grupo6.app.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;


public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    @Query("SELECT c FROM Cliente c WHERE c.persona.id = :p")
    Optional<Cliente> findByPersonaId (@Param("p") Integer idPersona);

}
