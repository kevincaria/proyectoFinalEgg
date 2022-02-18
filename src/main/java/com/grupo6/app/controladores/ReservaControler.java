package com.grupo6.app.controladores;

import com.grupo6.app.entidades.Habitacion;
import com.grupo6.app.entidades.Reserva;
import com.grupo6.app.servicios.CategoriaService;
import com.grupo6.app.servicios.HabitacionService;
import com.grupo6.app.servicios.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

@Controller
@RequestMapping("/reserva")
public class ReservaControler {
    @Autowired
    ReservaService servicioReserva;

    @Autowired
    HabitacionService servicioHabitacion;

    @Autowired
    CategoriaService servicioCategoria;

    @GetMapping("/formConsulta")
    public String consultarDisponibilidad(
            @RequestParam(required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate entrada,
            @RequestParam(required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate salida,
            @RequestParam(required = true) Integer adulto,
            @RequestParam(required = false) Integer ninos) {

        if (ninos == null) {
            ninos = 0;
        }

        int cantPersonas = adulto + ninos;

        Long cantDias = DAYS.between(entrada, salida); //calculo la cantidad de dias de las fechas ingresadas

        System.out.println("La cantidad de dias es de : " + cantDias);

        if (cantPersonas > 0) {
            List<Reserva> reservas = servicioReserva.traerTodoFechasIngresoSalida(entrada, salida);
            List<Habitacion> habitacions = servicioHabitacion.findByCategoriaCantidad(cantPersonas);
            for (Reserva r : reservas) {
                habitacions.remove(r.getHabitacion());
//                System.out.println("------------------------------------------------");
//                System.out.println("id: "+r.getId());
//                System.out.println("cantidad : "+r.getCantidadPersonas());
//                System.out.println("ingreso: "+r.getFechaIngreso());
//                System.out.println("salida: "+r.getFechaSalida());
//                System.out.println("estado: "+r.getEstado());
//                System.out.println("empleado: "+r.getEmpleado());
//                System.out.println("pago: "+r.getPago());
//                System.out.println("Hab.categoria: "+r.getHabitacion().getCategoria().getNombre());
//                System.out.println("Hab.desc: "+r.getHabitacion().getDescripcion());
//                System.out.println("Hab.id: "+r.getHabitacion().getId());
//                System.out.println("hab.nombre: "+r.getHabitacion().getNombre());
//                System.out.println("------------------------------------------------");
//
//                }
            }
            System.out.println("------------------MOSTRANDO HABITACIONES----------------");
            for (Habitacion h : habitacions) {
                System.out.println("Hab nombre: " + h.getNombre());
                System.out.println(h.getDescripcion());
                System.out.println(h.getCategoria().getNombre());
                System.out.println("------------------------------------------------");
            }

        }



        return "index";
    }
}