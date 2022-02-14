package com.grupo6.app.servicios;

import com.grupo6.app.entidades.Habitacion;
import com.grupo6.app.repositorios.HabitacionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ServicioHabitacionImpl implements IServicioHabitacion {

    @Autowired
    HabitacionDao habitacionDao;

    @Transactional(readOnly = true)
    @Override
    public List<Habitacion> listarHabitaciones() {
        return habitacionDao.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Habitacion buscarHabitacionPorId(Integer idHab) {
        return habitacionDao.findById(idHab).orElse(null);
    }

    @Transactional
    @Override
    public void guardarEditarHabitacion(Habitacion habitacion) {
        habitacionDao.save(habitacion);
    }

    @Transactional
    @Override
    public void eliminarHabitacion(Integer idHabitacion) {
        habitacionDao.delete(buscarHabitacionPorId(idHabitacion));
    }

    @Transactional(readOnly = true)
    @Override
    public Habitacion buscarPorNombreHab(String nombre) {
        return habitacionDao.findByNombre(nombre).orElse(null);
    }
}
