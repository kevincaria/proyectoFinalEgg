package com.grupo6.app.servicios;

import com.grupo6.app.entidades.Habitacion;
import com.grupo6.app.entidades.Reserva;

import java.time.LocalDate;
import java.util.List;

public interface IServicioHabitacion {
    List<Habitacion> listarHabitaciones();

    Habitacion buscarHabitacionPorId(Integer idHab);

    void guardarEditarHabitacion(Habitacion habitacion);

    void eliminarHabitacion(Integer idHabitacion);

    Habitacion buscarPorNombreHab(String nombre);

}
