package com.grupo6.app.controladores;

import com.grupo6.app.entidades.Pago;
import com.grupo6.app.servicios.PagoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pago")
public class PagoController {
    @Autowired
    private PagoService pagoService;

    @GetMapping("/lista")
    public String listar(Model model) {
        model.addAttribute("lista", pagoService.listarPagos());
        model.addAttribute("titulo", "Lista de Pagos");
        model.addAttribute("Pago", new Pago());
        return "pago-lista";
    }

    @GetMapping("/crear")
    public String toCrear(Model model) {
        model.addAttribute("titulo", "Crear Pago");
        model.addAttribute("Pago", new Pago());
        return "pago-form";
    }

    @PostMapping("/crear")
    public String crear(Pago Pago) {
        pagoService.crearPago(Pago);
        return "redirect:/pago/lista";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Integer id, Model model) {
        if (pagoService.findByIdPago(id) == null) {
            model.addAttribute("error", "Error al editar");
            return "redirect:/pago/lista";
        }
        model.addAttribute("titulo", "Editar Pago");
        model.addAttribute("Pago", pagoService.findByIdPago(id));
        return "pago-form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Integer id, Model model) {
        if (pagoService.findByIdPago(id) == null) {
            model.addAttribute("error", "Error al eliminar");
            return "redirect:/pago/lista";
        }
        pagoService.eliminarPago(id);
        return "redirect:/pago/lista";
    }
}
