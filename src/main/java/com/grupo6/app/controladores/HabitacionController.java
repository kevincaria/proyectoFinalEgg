package com.grupo6.app.controladores;

import com.grupo6.app.servicios.HabitacionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/habitacion")
public class HabitacionController {

    @Autowired
    HabitacionServiceImpl habitacionServiceImpl;

    @GetMapping("/listar")
    public String listarHabitaciones(Model model){

        model.addAttribute("listaHab", habitacionServiceImpl.listarHabitaciones());

        return "habitaciones-listar";
    }


}
