package com.grupo6.app.servicios;

import java.util.List;
import java.util.Optional;

import com.grupo6.app.entidades.Habitacion;

public interface HabitacionService {
    List<Habitacion> listarHabitaciones();

    void eliminarHabitacion(Integer id);

    void crearHabitacion(Habitacion Habitacion);

    Habitacion findByIdHabitacion(Integer id);

    Habitacion buscarPorNombreHabitacion(String nombre);

    List<Habitacion> findByCategoriaCantidad(Integer cantidad);
}
