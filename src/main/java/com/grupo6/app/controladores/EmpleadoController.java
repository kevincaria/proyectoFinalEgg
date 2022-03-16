package com.grupo6.app.controladores;

import com.grupo6.app.entidades.Empleado;
import com.grupo6.app.servicios.EmpleadoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/empleado")
public class EmpleadoController {
    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping("/lista")
    public String listar(Model model) {
        model.addAttribute("lista", empleadoService.listarEmpleados());
        model.addAttribute("titulo", "Lista de Empleados");
        model.addAttribute("Empleado", new Empleado());
        return "empleado-lista";
    }

    @GetMapping("/crear")
    public String toCrear(Model model) {
        model.addAttribute("titulo", "Crear Empleado");
        model.addAttribute("Empleado", new Empleado());
        return "empleado-form";
    }

    @PostMapping("/crear")
    public String crear(Empleado empleado) {
        empleadoService.crearEmpleado(empleado);
        return "redirect:/empleado/lista";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Integer id, Model model) {
        if (empleadoService.findByIdEmpleado(id) == null) {
            model.addAttribute("error", "Error al editar");
            return "redirect:/empleado/lista";
        }
        model.addAttribute("titulo", "Editar Empleado");
        model.addAttribute("Empleado", empleadoService.findByIdEmpleado(id));
        return "empleado-form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Integer id, Model model) {
        if (empleadoService.findByIdEmpleado(id) == null) {
            model.addAttribute("error", "Error al eliminar");
            return "redirect:/empleado/lista";
        }
        empleadoService.eliminarEmpleado(id);
        return "redirect:/Empleado/lista";
    }
}
