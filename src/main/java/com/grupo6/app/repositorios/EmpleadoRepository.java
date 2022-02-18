package com.grupo6.app.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.grupo6.app.entidades.Empleado;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {
}
