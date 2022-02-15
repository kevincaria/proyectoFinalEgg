package com.grupo6.app.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.grupo6.app.entidades.Reserva;
import java.time.LocalDate;
import java.util.List;

import java.util.Optional;



@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
    @Query("SELECT r FROM Reserva r WHERE r.fechaIngreso =:i and r.fechaSalida =:s")
    Optional<List<Reserva>> findAllFechasIngresoSalida (@Param("i") LocalDate inicio, @Param("s") LocalDate salida);

}
