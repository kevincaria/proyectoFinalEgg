package com.grupo6.app.servicios;

import com.grupo6.app.entidades.Categoria;
import com.grupo6.app.entidades.Habitacion;
import com.grupo6.app.entidades.Reserva;
import com.grupo6.app.errores.ErrorServicio;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ReservaService {

    List<Reserva> listarReservas();

    Reserva buscarReservaPorId(Integer idReserva);

    void guardarEditarReserva(Reserva reserva);

    void eliminarReserva(Integer idReserva);

    List<Habitacion> traerTodoFechasIngresoSalidaCantidad (LocalDate ingreso, LocalDate salida, Integer cantidadPersonas) throws ErrorServicio;

    Boolean validarReserva(LocalDate ingreso, LocalDate salida, Integer cantidadPersonas, Categoria categoria) throws ErrorServicio;

    List<Reserva> findAllByQ(String q)throws ErrorServicio;
}
