package com.grupo6.app.controladores;

import com.grupo6.app.entidades.Cliente;
import com.grupo6.app.entidades.Habitacion;
import com.grupo6.app.entidades.Persona;
import com.grupo6.app.entidades.Reserva;
import com.grupo6.app.errores.ErrorServicio;
import com.grupo6.app.servicios.ClienteService;
import com.grupo6.app.servicios.HabitacionService;
import com.grupo6.app.servicios.PersonaService;
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

    @Autowired
    PersonaService servicioPersona;

    @Autowired
    ClienteService servicioCliente;

    @GetMapping("/listar")
    public String listarReservas(Model model, @RequestParam(required = false) String q){

        if(q != null && !q.isEmpty()){
            model.addAttribute("titulo","Listas de Reservas");

            try {
                model.addAttribute("reservas", servicioReserva.findAllByQ(q));
                return "lista/reserva-lista";
            } catch (ErrorServicio e) {
               model.addAttribute("error",e.getMessage());
                return "lista/reserva-lista";
            }
        }

        model.addAttribute("titulo","Listas de Reservas");
        model.addAttribute("reservas",servicioReserva.listarReservas());

        return "lista/reserva-lista";
    }

    @GetMapping("/formReserva")
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
                    return "formulario/Form-reserva";
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
    public String formulario(Reserva reserva, Model model) {

        try {
            Persona persona = servicioPersona.buscarDniPersona(reserva.getCliente().getPersona().getDni());
            if (persona != null) {
                reserva.setCliente(servicioCliente.buscarClientePorIdPersona(persona.getId()));
                reserva.setAlta(false);
                reserva.setHabitacion(servicioHabitacion.findByIdHabitacion(reserva.getHabitacion().getId()));

            } else {
//                List<Habitacion> habDisponibles = new ArrayList<>();
//                habDisponibles = servicioReserva.traerTodoFechasIngresoSalidaCantidad(reserva.getFechaIngreso(), reserva.getFechaSalida(), reserva.getCantidadPersonas());
//                model.addAttribute("habDisponibles", habDisponibles);
//                model.addAttribute("reserva", reserva);
//                model.addAttribute("Cliente", true);
                Cliente cliente = new Cliente();
                persona = new Persona();
                persona.setDni(reserva.getCliente().getPersona().getDni());
                cliente.setPersona(persona);
                model.addAttribute("cliente",cliente);
                return "formulario/Form-Cliente";
            }
                if (servicioReserva.validarReserva(reserva.getFechaIngreso(), reserva.getFechaSalida(), reserva.getCantidadPersonas(), reserva.getHabitacion().getCategoria())) {

                    servicioReserva.guardarEditarReserva(reserva);
                    return "redirect:/reserva/listar";
            }
        } catch (ErrorServicio e) {

            model.addAttribute("error", e.getMessage());
            return "redirect:/reserva/listar";
        }

        return "redirect:/reserva/listar";

    }

    @RequestMapping("/editar/{id}")
    public String editarReserva(@PathVariable("id") Integer id, Model model){

        try {
            List<Habitacion> habDisponibles = new ArrayList<>();
            Reserva reserva = servicioReserva.buscarReservaPorId(id);
            model.addAttribute("Cliente",true);
            model.addAttribute("reserva", reserva);
            habDisponibles = servicioReserva.traerTodoFechasIngresoSalidaCantidad(reserva.getFechaIngreso(), reserva.getFechaSalida(), reserva.getCantidadPersonas());
            model.addAttribute("titulo", "Editar Reserva");
            model.addAttribute("habDisponibles", habDisponibles);
            return "formulario/Form-reserva";
        }catch (ErrorServicio e){
            model.addAttribute("error",e.getMessage());
            return "redirect:/reserva/listar";
        }

    }

    @GetMapping("/eliminar/{id}")
    public String eliminarReserva (@PathVariable("id") Integer id){
        servicioReserva.eliminarReserva(id);
        return "redirect:/reserva/listar";
    }

}