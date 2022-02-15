package com.grupo6.app.repositorios;

import com.grupo6.app.entidades.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface HabitacionRepository extends JpaRepository<Habitacion, Integer> {
    Optional<Habitacion> findByNombre(String nombre);
}
