package com.grupo6.app.controladores;

import com.grupo6.app.servicios.IServicioHabitacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/habitacion")
public class HabitacionControlador {

    @Autowired
    IServicioHabitacion habitacionImpl;

    @GetMapping("/listar")
    public String listarHabitaciones(Model model){

        model.addAttribute("listaHab", habitacionImpl.listarHabitaciones());

        return "habitaciones-listar";
    }


}