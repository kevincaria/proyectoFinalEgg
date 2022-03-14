package com.grupo6.app.controladores;

import com.grupo6.app.entidades.Cliente;
import com.grupo6.app.servicios.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cliente")
public class ClienteControlador {

    @Autowired
    ClienteService clienteService;

    @GetMapping("listar")
    public String ListarClientes (Model model){
        model.addAttribute("titulo","Lista de Clientes");
        model.addAttribute("clientes",clienteService.listarClientes());
        model.addAttribute("cliente",new Cliente());
        return "lista/cliente-lista";
    }

    @PostMapping("/formCliente")
    public String crearCliente(Cliente cliente){
        clienteService.crearCliente(cliente);
        return "redirect:/cliente/lista";
    }
}
