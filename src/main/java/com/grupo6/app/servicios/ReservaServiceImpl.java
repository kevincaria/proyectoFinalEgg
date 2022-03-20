package com.grupo6.app.servicios;

import com.grupo6.app.entidades.Categoria;
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
    public List<Habitacion> traerTodoFechasIngresoSalidaCantidad(LocalDate ingreso, LocalDate salida, Integer cantidadPersonas) throws ErrorServicio {

        if(ingreso.isAfter(salida) || salida.isBefore(ingreso)){
            throw new ErrorServicio("Las fechas estan mal ingresadas verifique entrada:"+ingreso+" salida:"+salida);
        }

        if(cantidadPersonas < 0){
            throw new ErrorServicio("No puede ingreser numero de personas negativo");
        }

        return reservaRepository.findAllFechasIngresoSalidaCantidad(ingreso,salida,cantidadPersonas);
    }

    @Override
    public Boolean validarReserva(LocalDate ingreso, LocalDate salida, Integer cantidadPersonas, Categoria categoria) throws ErrorServicio{
        boolean flag =true;
        if(ingreso.isAfter(salida) || salida.isBefore(ingreso)){
            flag=false;
            throw new ErrorServicio("Las fechas estan mal ingresadas verifique entrada:"+ingreso+" salida:"+salida);
        }

        if(cantidadPersonas < 0){
            flag=false;
            throw new ErrorServicio("No puede ingreser numero de personas negativo");
        }

        if(cantidadPersonas != categoria.getCantidad()){
            flag=false;
            throw new ErrorServicio("La cantidad de personas no puede ser distinta a la categoria");
        }

        return flag;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Reserva> findAllByQ(String q) throws ErrorServicio {
        List<Reserva> reservas = reservaRepository.findAllByQ("%"+q+"%");
        if(reservas.isEmpty()){
            throw new ErrorServicio("No se econtraron resultados con la palabra : "+q);
        }
        return reservas;

    }

}
