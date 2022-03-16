package com.grupo6.app.controladores;

import com.grupo6.app.entidades.Cliente;
import com.grupo6.app.servicios.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping("/lista")
    public String listar(Model model) {
        model.addAttribute("lista", clienteService.listarClientes());
        model.addAttribute("titulo", "Lista de Clientes");
        model.addAttribute("Cliente", new Cliente());
        return "cliente-lista";
    }

    @GetMapping("/crear")
    public String toCrear(Model model) {
        model.addAttribute("titulo", "Crear Cliente");
        model.addAttribute("Cliente", new Cliente());
        return "cliente-form";
    }

    @PostMapping("/crear")
    public String crear(Cliente cliente) {
        clienteService.crearCliente(cliente);
        return "redirect:/cliente/lista";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Integer id, Model model) {
        if (clienteService.findByIdCliente(id) == null) {
            model.addAttribute("error", "Error al editar");
            return "redirect:/cliente/lista";
        }
        model.addAttribute("titulo", "Editar Cliente");
        model.addAttribute("Cliente", clienteService.findByIdCliente(id));
        return "cliente-form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Integer id, Model model) {
        if (clienteService.findByIdCliente(id) == null) {
            model.addAttribute("error", "Error al eliminar");
            return "redirect:/cliente/lista";
        }
        clienteService.eliminarCliente(id);
        return "redirect:/cliente/lista";
    }
}
