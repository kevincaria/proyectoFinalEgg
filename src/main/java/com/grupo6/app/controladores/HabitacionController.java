package com.grupo6.app.controladores;

import com.grupo6.app.entidades.Habitacion;
import com.grupo6.app.servicios.HabitacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/habitacion")
public class HabitacionController {

    @Autowired
    HabitacionService habitacionService;

    @GetMapping("/listar")
    public String listarHabitaciones(Model model){

        model.addAttribute("listaHab", habitacionService.listarHabitaciones());

        return "habitaciones-listar";
    }

    @GetMapping("/crear")
    public String toCrear(Model model) {
        model.addAttribute("titulo", "Crear Habitacion");
        model.addAttribute("Habitacion", new Habitacion());
        return "Habitacion-form";
    }

    @PostMapping("/crear")
    public String crear(Habitacion Habitacion) {
        habitacionService.crearHabitacion(Habitacion);
        return "redirect:/habitacion/lista";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Integer id, Model model) {
        if (habitacionService.findByIdHabitacion(id) == null) {
            model.addAttribute("error", "Error al editar");
            return "redirect:/habitacion/lista";
        }
        model.addAttribute("titulo", "Editar Habitacion");
        model.addAttribute("Habitacion", habitacionService.findByIdHabitacion(id));
        return "Habitacion-form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Integer id, Model model) {
        if (habitacionService.findByIdHabitacion(id) == null) {
            model.addAttribute("error", "Error al eliminar");
            return "redirect:/Habitacion/lista";
        }
        habitacionService.eliminarHabitacion(id);
        return "redirect:/Habitacion/lista";
    }

}
