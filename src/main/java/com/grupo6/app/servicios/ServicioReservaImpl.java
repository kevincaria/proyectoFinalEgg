package com.grupo6.app.servicios;

import com.grupo6.app.entidades.Reserva;
import com.grupo6.app.repositorios.ReservaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class ServicioReservaImpl implements IServicioReserva {

    @Autowired
    ReservaDao reservaDao;

    @Transactional(readOnly = true)
    @Override
    public List<Reserva> listarReservas() {
        return reservaDao.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Reserva buscarReservaPorId(Integer idReserva) {
        return reservaDao.findById(idReserva).orElse(null);
    }

    @Transactional
    @Override
    public void guardarEditarReserva(Reserva reserva) {
        reservaDao.save(reserva);
    }

    @Transactional
    @Override
    public void eliminarReserva(Integer idReserva) {
        reservaDao.delete(buscarReservaPorId(idReserva));
    }

    @Override
    public List<Reserva> traerTodoFechasIngresoSalida(LocalDate ingreso, LocalDate salida) {
        return reservaDao.findAllFechasIngresoSalida(ingreso,salida).orElse(null);
    }
}
