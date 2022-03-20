package com.grupo6.app.controladores;

import com.grupo6.app.entidades.Habitacion;
import com.grupo6.app.servicios.CategoriaService;
import com.grupo6.app.servicios.HabitacionService;
import com.grupo6.app.servicios.NivelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/habitacion")
public class HabitacionControlador {

    @Autowired
    HabitacionService habitacionService;

    @Autowired
    CategoriaService categoriaService;

    @Autowired
    NivelService nivelService;

    @GetMapping("/listar")
    public String listarHabitaciones(Model model){

        model.addAttribute("titulo","Habitaciones");
        model.addAttribute("listaHab", habitacionService.listarHabitaciones());
        return "lista/habitaciones-listar";
    }

    @GetMapping("/crear")
    public String toCrear(Model model) {
        model.addAttribute("titulo", "Crear Habitacion");
        model.addAttribute("habitacion", new Habitacion());
        model.addAttribute("categoriaList", categoriaService.listarCategorias());
        model.addAttribute("niveles", nivelService.listarNiveles());
        return "formulario/Form-Habitacion";
    }

    @PostMapping("/crear")
    public String crear(Habitacion habitacion) {
        habitacion.setCategoria(categoriaService.findByIdCategoria(habitacion.getCategoria().getId()));
        habitacion.setNivel(nivelService.findByIdNivel(habitacion.getNivel().getId()));
        habitacionService.crearHabitacion(habitacion);
        return "redirect:/habitacion/listar";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Integer id, Model model) {
        if (habitacionService.findByIdHabitacion(id) == null) {
            model.addAttribute("error", "Error al editar");
            return "redirect:/habitacion/listar";
        }
        model.addAttribute("titulo", "Editar Habitacion");
        model.addAttribute("habitacion", habitacionService.findByIdHabitacion(id));
        model.addAttribute("categoriaList", categoriaService.listarCategorias());
        model.addAttribute("niveles", nivelService.listarNiveles());
        return "formulario/Form-Habitacion";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Integer id, Model model) {
        if (habitacionService.findByIdHabitacion(id) == null) {
            model.addAttribute("error", "Error al eliminar");
            return "redirect:/habitacion/listar";
        }
        habitacionService.eliminarHabitacion(id);
        return "redirect:/habitacion/listar";
    }


}
