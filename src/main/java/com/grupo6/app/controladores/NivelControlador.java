package com.grupo6.app.controladores;

import com.grupo6.app.entidades.Nivel;
import com.grupo6.app.servicios.NivelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/nivel")
public class NivelControlador {
    @Autowired
    private NivelService nivelService;

    @GetMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("niveles", nivelService.listarNiveles());
        model.addAttribute("titulo", "Lista de Niveles");
        return "lista/nivel-lista";
    }

    @GetMapping("/crear")
    public String toCrear(Model model) {
        model.addAttribute("titulo", "Crear Nivel");
        model.addAttribute("nivel", new Nivel());
        return "formulario/Form-Nivel";
    }

    @PostMapping("/crear")
    public String crear(Nivel nivel) {
        nivelService.crearNivel(nivel);
        return "redirect:/nivel/listar";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Integer id, Model model) {
        if (nivelService.findByIdNivel(id) == null) {
            model.addAttribute("error", "Error al editar");
            return "redirect:/nivel/listar";
        }
        model.addAttribute("titulo", "Editar Nivel");
        model.addAttribute("nivel", nivelService.findByIdNivel(id));
        return "formulario/Form-Nivel";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Integer id, Model model) {
        if (nivelService.findByIdNivel(id) == null) {
            model.addAttribute("error", "Error al eliminar");
            return "redirect:/nivel/listar";
        }
        nivelService.eliminarNivel(id);
        return "redirect:/nivel/listar";
    }

}
