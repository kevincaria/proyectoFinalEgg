package com.grupo6.app.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.grupo6.app.entidades.Reserva;

import java.util.Optional;



@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
    Optional<Reserva> findByNombreReserva(String nombre);
}
