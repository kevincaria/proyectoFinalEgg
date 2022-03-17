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
public class ClienteControlador {

    @Autowired
    ClienteService clienteService;

    @GetMapping("/listar")
    public String ListarClientes (Model model){
        model.addAttribute("titulo","Lista de Clientes");
        model.addAttribute("clientes",clienteService.listarClientes());

        return "lista/cliente-lista";
    }

    @GetMapping("/crear")
    public String crearClienteGet(Model model){
        model.addAttribute("cliente",new Cliente());
        return "formulario/Form-Cliente";
    }

    @PostMapping("/formCliente")
    public String crearClientePost(Cliente cliente){
        clienteService.crearCliente(cliente);
        return "redirect:/cliente/listar";
    }

    @GetMapping("/editar/{id}")
    public String editarCliente(@PathVariable("id") Integer id, Model model){
        model.addAttribute("cliente",clienteService.findByIdCliente(id));
        model.addAttribute("titulo","Editar Cliente");
        return "formulario/Form-Cliente";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCliente (@PathVariable("id") Integer id, Model model){
        clienteService.eliminarCliente(id);
        return "redirect:/cliente/listar";
    }
}
