package com.grupo6.app.servicios;

import com.grupo6.app.entidades.Habitacion;
import com.grupo6.app.entidades.Reserva;
import com.grupo6.app.errores.ErrorServicio;

import java.time.LocalDate;
import java.util.List;

public interface ReservaService {

    List<Reserva> listarReservas();

    Reserva buscarReservaPorId(Integer idReserva);

    void guardarEditarReserva(Reserva reserva);

    void eliminarReserva(Integer idReserva);

    List<Habitacion> traerTodoFechasIngresoSalidaCantidad (LocalDate ingreso, LocalDate salida, Integer cantidadPersonas) throws ErrorServicio;

}
