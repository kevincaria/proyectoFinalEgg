package com.grupo6.app.controladores;

import com.grupo6.app.entidades.Categoria;
import com.grupo6.app.servicios.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/lista")
    public String listar(Model model) {
        model.addAttribute("lista", categoriaService.listarCategorias());
        model.addAttribute("titulo", "Lista de Categoriaes");
        model.addAttribute("Categoria", new Categoria());
        return "categoria-lista";
    }

    @GetMapping("/crear")
    public String toCrear(Model model) {
        model.addAttribute("titulo", "Crear Categoria");
        model.addAttribute("Categoria", new Categoria());
        return "categoria-form";
    }

    @PostMapping("/crear")
    public String crear(Categoria categoria) {
        categoriaService.crearCategoria(categoria);
        return "redirect:/categoria/lista";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Integer id, Model model) {
        if (categoriaService.findByIdCategoria(id) == null) {
            model.addAttribute("error", "Error al editar");
            return "redirect:/categoria/lista";
        }
        model.addAttribute("titulo", "Editar Categoria");
        model.addAttribute("Categoria", categoriaService.findByIdCategoria(id));
        return "categoria-form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Integer id, Model model) {
        if (categoriaService.findByIdCategoria(id) == null) {
            model.addAttribute("error", "Error al eliminar");
            return "redirect:/categoria/lista";
        }
        categoriaService.eliminarCategoria(id);
        return "redirect:/categoria/lista";
    }
}
