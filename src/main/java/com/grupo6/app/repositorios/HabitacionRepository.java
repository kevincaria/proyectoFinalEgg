package com.grupo6.app.repositorios;

import com.grupo6.app.entidades.Habitacion;
import com.grupo6.app.entidades.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Repository
public interface HabitacionRepository extends JpaRepository<Habitacion, Integer> {
    Optional<Habitacion> findByNombre(String nombre);

    @Query("SELECT h FROM Habitacion h WHERE h.categoria.cantidad = :c")
    List<Habitacion> findByCategoriaCantidad(@Param("c")Integer cantidad);

}
