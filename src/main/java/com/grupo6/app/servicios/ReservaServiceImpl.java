package com.grupo6.app.servicios;

import com.grupo6.app.entidades.Reserva;
import com.grupo6.app.repositorios.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservaServiceImpl implements ReservaService {

    @Autowired
    ReservaRepository reservaRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Reserva> listarReservas() {
        return reservaRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Reserva buscarReservaPorId(Integer idReserva) {
        return reservaRepository.findById(idReserva).orElse(null);
    }

    @Transactional
    @Override
    public void guardarEditarReserva(Reserva reserva) {
        reservaRepository.save(reserva);
    }

    @Transactional
    @Override
    public void eliminarReserva(Integer idReserva) {
        reservaRepository.delete(buscarReservaPorId(idReserva));
    }

    @Override
    public List<Reserva> traerTodoFechasIngresoSalida(LocalDate ingreso, LocalDate salida) {
        return reservaRepository.findAllFechasIngresoSalida(ingreso,salida).orElse(null);
    }
}
