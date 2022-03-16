package com.grupo6.app.controladores;

import com.grupo6.app.entidades.Huesped;
import com.grupo6.app.servicios.HuespedService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/huesped")
public class HuespedController {
    @Autowired
    private HuespedService huespedService;

    @GetMapping("/lista")
    public String listar(Model model) {
        model.addAttribute("lista", huespedService.listarHuespedes());
        model.addAttribute("titulo", "Lista de Huespedes");
        model.addAttribute("Huesped", new Huesped());
        return "huesped-lista";
    }

    @GetMapping("/crear")
    public String toCrear(Model model) {
        model.addAttribute("titulo", "Crear Huesped");
        model.addAttribute("Huesped", new Huesped());
        return "huesped-form";
    }

    @PostMapping("/crear")
    public String crear(Huesped huesped) {
        huespedService.crearHuesped(huesped);
        return "redirect:/huesped/lista";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Integer id, Model model) {
        if (huespedService.findByIdHuesped(id) == null) {
            model.addAttribute("error", "Error al editar");
            return "redirect:/huesped/lista";
        }
        model.addAttribute("titulo", "Editar Huesped");
        model.addAttribute("Huesped", huespedService.findByIdHuesped(id));
        return "huesped-form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Integer id, Model model) {
        if (huespedService.findByIdHuesped(id) == null) {
            model.addAttribute("error", "Error al eliminar");
            return "redirect:/huesped/lista";
        }
        huespedService.eliminarHuesped(id);
        return "redirect:/huesped/lista";
    }
}
