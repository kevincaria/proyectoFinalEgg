package com.grupo6.app.controladores;

import com.grupo6.app.entidades.Reserva;
import com.grupo6.app.servicios.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/reserva")
public class ReservaController {
    @Autowired
    ReservaService servicioReserva;

    @GetMapping("/formConsulta")
    public String consultarDisponibilidad(
            @RequestParam(required = true)@DateTimeFormat(pattern="yyyy-MM-dd") Date entrada,
            @RequestParam(required = true)@DateTimeFormat(pattern="yyyy-MM-dd") Date salida,
            @RequestParam(required = false) Integer adulto,
            @RequestParam(required = false) Integer ninos)  {

        System.out.println(entrada);
        System.out.println(salida);

        LocalDate ingreso = entrada.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); // combierte date to LocalDate
        LocalDate egreso = salida.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); // combierte date to LocalDate
        List<Reserva> Reservas = servicioReserva.traerTodoFechasIngresoSalida(ingreso, egreso);

        for (Reserva aux : Reservas) {
            System.out.println(aux);
        }

        return "index";
    }
}