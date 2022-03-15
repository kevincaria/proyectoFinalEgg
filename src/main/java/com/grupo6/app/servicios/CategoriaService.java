package com.grupo6.app.servicios;

import java.util.List;
import com.grupo6.app.entidades.Categoria;

public interface CategoriaService {
    public List<Categoria> listarCategorias();
    public void eliminarCategoria(Integer id);
    public void crearCategoria(Categoria Categoria);
    public Categoria findByIdCategoria(Integer id);
    public Categoria buscarPorNombreCategoria(String nombre);
}
