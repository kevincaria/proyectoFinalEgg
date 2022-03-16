package com.grupo6.app.servicios;

import java.util.List;
import com.grupo6.app.entidades.Habitacion;

public interface HabitacionService {
    public List<Habitacion> listarHabitaciones();
    public void eliminarHabitacion(Integer id);
    public void crearHabitacion(Habitacion Habitacion);
    public Habitacion findByIdHabitacion(Integer id);
    public Habitacion buscarPorNombreHabitacion(String nombre);
}
