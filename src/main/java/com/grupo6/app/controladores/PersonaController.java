package com.grupo6.app.controladores;

import com.grupo6.app.entidades.Persona;
import com.grupo6.app.servicios.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/persona")
public class PersonaController {
    @Autowired
    private PersonaService personaService;

    @GetMapping("/lista")
    public String listar(Model model) {
        model.addAttribute("lista", personaService.listarPersonas());
        model.addAttribute("titulo", "Lista de Personas");
        model.addAttribute("Persona", new Persona());
        return "persona-lista";
    }

    @GetMapping("/crear")
    public String toCrear(Model model) {
        model.addAttribute("titulo", "Crear Persona");
        model.addAttribute("Persona", new Persona());
        return "persona-form";
    }

    @PostMapping("/crear")
    public String crear(Persona persona) {
        personaService.crearPersona(persona);
        return "redirect:/persona/lista";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Integer id, Model model) {
        if (personaService.findByIdPersona(id) == null) {
            model.addAttribute("error", "Error al editar");
            return "redirect:/persona/lista";
        }
        model.addAttribute("titulo", "Editar Persona");
        model.addAttribute("Persona", personaService.findByIdPersona(id));
        return "Persona-form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Integer id, Model model) {
        if (personaService.findByIdPersona(id) == null) {
            model.addAttribute("error", "Error al eliminar");
            return "redirect:/persona/lista";
        }
        personaService.eliminarPersona(id);
        return "redirect:/persona/lista";
    }
}
