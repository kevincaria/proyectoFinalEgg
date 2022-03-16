package com.grupo6.app.servicios;

import com.grupo6.app.entidades.Reserva;

import java.time.LocalDate;
import java.util.List;

public interface ReservaService {

    List<Reserva> listarReservas();

    Reserva buscarReservaPorId(Integer idReserva);

    void guardarEditarReserva(Reserva reserva);

    void eliminarReserva(Integer idReserva);

    List<Reserva> traerTodoFechasIngresoSalida (LocalDate ingreso, LocalDate salida);

}
