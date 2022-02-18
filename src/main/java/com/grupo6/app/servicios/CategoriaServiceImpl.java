package com.grupo6.app.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.grupo6.app.entidades.Categoria;
import com.grupo6.app.repositorios.CategoriaRepository;

@Service
public class CategoriaServiceImpl implements CategoriaService{

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    @Override
    @Transactional
    public void eliminarCategoria(Integer id) {
        categoriaRepository.delete(findByIdCategoria(id));
    }

    @Override
    @Transactional
    public void crearCategoria(Categoria categoria) {
        categoriaRepository.save(categoria);
    }

    @Override
    @Transactional(readOnly = true)
    public Categoria findByIdCategoria(Integer id) {
        return categoriaRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Categoria buscarPorNombreCategoria(String nombre) {
        return categoriaRepository.findByNombre(nombre).orElse(null);
    }

    @Override
    public List<Categoria> buscarPorCantidad(Integer cantidad) {
        return categoriaRepository.findByCantidad(cantidad);
    }

}
