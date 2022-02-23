package com.grupo6.app.controladores;

import com.grupo6.app.entidades.Habitacion;
import com.grupo6.app.entidades.Reserva;
import com.grupo6.app.repositorios.ReservaRepository;
import com.grupo6.app.servicios.CategoriaService;
import com.grupo6.app.servicios.HabitacionService;
import com.grupo6.app.servicios.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    ReservaRepository reservaRepository;

    @GetMapping("/listar")
    public String listarReservas(Model model){
        model.addAttribute("titulo","Listas de Reservas");
        model.addAttribute("reservas",servicioReserva.listarReservas());
        return "reserva-listar";
    }

    @GetMapping("/formConsulta")
    public String consultarDisponibilidad(
            @RequestParam(required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate entrada,
            @RequestParam(required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate salida,
            @RequestParam(required = true) Integer adulto,
            @RequestParam(required = false) Integer ninos,
            Model model) {

        if (ninos == null) {
            ninos = 0;
        }

        int cantPersonas = adulto + ninos;

        Long cantDias = DAYS.between(entrada, salida); //calculo la cantidad de dias de las fechas ingresadas

        System.out.println("La cantidad de dias es de : " + cantDias);

        if (cantPersonas > 0) {
//            List<Reserva> reservas = servicioReserva.traerTodoFechasIngresoSalida(entrada, salida);
//            List<Habitacion> habitacions = servicioHabitacion.findByCategoriaCantidad(cantPersonas);
            List<Habitacion> hab2 = reservaRepository.findAllFechasIngresoSalida2(entrada,salida,cantPersonas);
//            for (Reserva r : reservas) {
//                habitacions.remove(r.getHabitacion());
//            }

            if(!hab2.isEmpty()){
                Reserva reserva = new Reserva();
                reserva.setFechaSalida(salida);
                reserva.setFechaIngreso(entrada);
                reserva.setCantidadPersonas(cantPersonas);
                model.addAttribute("reserva",reserva);
                model.addAttribute("habDisponibles",hab2);
                return "reserva-form";
            }

        }

        return "index";
    }

    @PostMapping("/form")
    public String formulario(Reserva reserva){

        System.out.println("ID HAB SELECCIONADo   "+reserva.getHabitacion().getId());
        reserva.setHabitacion(servicioHabitacion.findByIdHabitacion(reserva.getHabitacion().getId()));
        servicioReserva.guardarEditarReserva(reserva);
        return "redirect:/reserva/listar";
    }

}