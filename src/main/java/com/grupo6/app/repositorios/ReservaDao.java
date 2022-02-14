package com.grupo6.app.repositorios;

import com.grupo6.app.entidades.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReservaDao extends JpaRepository<Reserva, Integer> {

    @Query("SELECT r FROM Reserva r WHERE r.fechaIngreso =:i and r.fechaSalida =:s")
    Optional<List<Reserva>> findAllFechasIngresoSalida (@Param("i") LocalDate inicio, @Param("s") LocalDate salida);


}
