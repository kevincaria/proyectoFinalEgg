package com.grupo6.app.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.grupo6.app.entidades.Habitacion;
import com.grupo6.app.repositorios.HabitacionRepository;

@Service
public class HabitacionServiceImpl implements HabitacionService {

    @Autowired
    private HabitacionRepository habitacionRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Habitacion> listarHabitaciones() {
        return habitacionRepository.findAll();
    }

    @Override
    @Transactional
    public void eliminarHabitacion(Integer id) {
        habitacionRepository.delete(findByIdHabitacion(id));
    }

    @Override
    @Transactional
    public void crearHabitacion(Habitacion habitacion) {
        habitacionRepository.save(habitacion);
    }

    @Override
    @Transactional(readOnly = true)
    public Habitacion findByIdHabitacion(Integer id) {
        return habitacionRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Habitacion buscarPorNombreHabitacion(String nombre) {
        return habitacionRepository.findByNombre(nombre).orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Habitacion> findByCategoriaCantidad(Integer cantidad) {
        return habitacionRepository.findByCategoriaCantidad(cantidad);
    }


}
