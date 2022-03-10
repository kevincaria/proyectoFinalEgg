package com.grupo6.app.controladores;

import com.grupo6.app.entidades.Habitacion;
import com.grupo6.app.entidades.Reserva;
import com.grupo6.app.errores.ErrorServicio;
import com.grupo6.app.servicios.HabitacionService;
import com.grupo6.app.servicios.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

@Controller
@RequestMapping("/reserva")
public class ReservaControler {
    @Autowired
    ReservaService servicioReserva;

    @Autowired
    HabitacionService servicioHabitacion;

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

        if(ninos == null || ninos < 0) {
            ninos = 0;
        }
        if(adulto == null || adulto < 0){
            adulto = 0;
            model.addAttribute("error", "Debe ingresear cantidad la cantidad Adultos");
            return "index";
        }

        int cantPersonas = adulto + ninos;
        if(cantPersonas > 0) {

            Long cantDias = DAYS.between(entrada, salida); //calculo la cantidad de dias de las fechas ingresadas
            System.out.println("La cantidad de dias es de : " + cantDias);
            List<Habitacion> habDisponibles = new ArrayList<>();
            try {
                habDisponibles = servicioReserva.traerTodoFechasIngresoSalidaCantidad(entrada, salida, cantPersonas);
                if (!habDisponibles.isEmpty()) {
                    Reserva reserva = new Reserva();
                    reserva.setFechaSalida(salida);
                    reserva.setFechaIngreso(entrada);
                    reserva.setCantidadPersonas(cantPersonas);
                    model.addAttribute("reserva", reserva);
                    model.addAttribute("habDisponibles", habDisponibles);
                    return "reserva-form";
                }
            } catch (ErrorServicio e) {
                model.addAttribute("error", e.getMessage());
                return "index";
            }

        }else {
            model.addAttribute("error","Debe ingresar una cantidad de adultos y/o niños");
        }

        return "index";
    }

    @PostMapping("/form")
    public String formulario(Reserva reserva, String flag){

        if(flag.equals("1")){
            reserva.setAlta(true);
        }else {
            reserva.setAlta(false);
        }

        System.out.println("ID HAB SELECCIONADO  "+reserva.getHabitacion().getId());
        reserva.setHabitacion(servicioHabitacion.findByIdHabitacion(reserva.getHabitacion().getId()));
        servicioReserva.guardarEditarReserva(reserva);
        return "redirect:/reserva/listar";
    }

    @GetMapping("/editar/{id}")
    public String editarReserva(@PathVariable("id") Integer id, Model model){

        try {
            List<Habitacion> habDisponibles = new ArrayList<>();
            Reserva reserva = servicioReserva.buscarReservaPorId(id);
            model.addAttribute("reserva", reserva);
            habDisponibles = servicioReserva.traerTodoFechasIngresoSalidaCantidad(reserva.getFechaIngreso(), reserva.getFechaSalida(), reserva.getCantidadPersonas());
            model.addAttribute("titulo", "Editar Reserva");
            model.addAttribute("habDisponibles", habDisponibles);
        }catch (ErrorServicio e){
            model.addAttribute("error",e.getMessage());
            return "reserva-form";
        }
        return "reserva-form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarReserva (@PathVariable("id") Integer id, Model model){

        servicioReserva.eliminarReserva(id);
        model.addAttribute("msj","Se elimino la reserva id " + id);

        return "redirect:/reserva/listar";
    }

}