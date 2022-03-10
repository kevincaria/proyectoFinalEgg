package com.grupo6.app.servicios;

import com.grupo6.app.entidades.Habitacion;
import com.grupo6.app.entidades.Reserva;
import com.grupo6.app.errores.ErrorServicio;
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

    @Transactional(readOnly = true)
    @Override
<<<<<<< HEAD
    public List<Reserva> traerTodoFechasIngresoSalida(LocalDate ingreso, LocalDate salida) {
        return reservaRepository.findAllFechasIngresoSalida(ingreso,salida);
=======
    public List<Habitacion> traerTodoFechasIngresoSalidaCantidad(LocalDate ingreso, LocalDate salida, Integer cantidadPersonas) throws ErrorServicio {

        if(ingreso.isAfter(salida) || salida.isBefore(ingreso)){
            throw new ErrorServicio("Las fechas estan mal ingresadas verifique entrada:"+ingreso+" salida:"+salida);
        }

        if(cantidadPersonas < 0){
            throw new ErrorServicio("No puede ingreser numero de personas negativo");
        }

        return reservaRepository.findAllFechasIngresoSalidaCantidad(ingreso,salida,cantidadPersonas);
>>>>>>> origin/alexis
    }

}
