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
public class CategoriaControlador {

    @Autowired
    CategoriaService categoriaService;

    @GetMapping("/listar")
    public String listarCategorias(Model model){
        model.addAttribute("categorias",categoriaService.listarCategorias());
        model.addAttribute("titulo","Lista de Categorias");
        return "lista/categoria-lista";
    }

    @GetMapping("/crear")
    public String crearCegoria(Model model){
        model.addAttribute("titulo","Crear Categoria");
        model.addAttribute("categoria",new Categoria());
        return "formulario/Form-Categoria";
    }

    @PostMapping("/formCategoria")
    public String guadarCategoria(Categoria categoria){

        categoriaService.crearCategoria(categoria);
        return "redirect:/categoria/listar";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Integer id, Model model) {
        if (categoriaService.findByIdCategoria(id) == null) {
            model.addAttribute("error", "Error al editar");
            return "redirect:/categoria/listar";
        }
        model.addAttribute("titulo", "Editar Categoria");
        model.addAttribute("categoria", categoriaService.findByIdCategoria(id));
        return "formulario/Form-Categoria";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Integer id, Model model) {
        if (categoriaService.findByIdCategoria(id) == null) {
            model.addAttribute("error", "Error al eliminar");
            return "redirect:/categoria/listar";
        }
        categoriaService.eliminarCategoria(id);
        return "redirect:/categoria/listar";
    }






}
