package com.grupo6.app.repositorios;

import com.grupo6.app.entidades.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HabitacionDao extends JpaRepository<Habitacion, Integer> {

    Optional<Habitacion> findByNombre(String nombre);
}
