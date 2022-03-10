package com.grupo6.app.servicios;

import java.util.List;

import com.grupo6.app.entidades.Categoria;

public interface CategoriaService {
     List<Categoria> listarCategorias();

     void eliminarCategoria(Integer id);

     void crearCategoria(Categoria Categoria);

     Categoria findByIdCategoria(Integer id);

     Categoria buscarPorNombreCategoria(String nombre);

     List<Categoria> buscarPorCantidad(Integer cantidad);


}
