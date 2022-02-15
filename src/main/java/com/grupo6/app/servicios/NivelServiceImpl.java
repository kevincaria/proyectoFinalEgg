package com.grupo6.app.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.grupo6.app.entidades.Nivel;
import com.grupo6.app.repositorios.NivelRepository;

@Service
public class NivelServiceImpl implements NivelService{

    @Autowired
    private NivelRepository nivelRepository;
    
    @Override
    @Transactional(readOnly = true)
    public List<Nivel> listarNiveles() {
        return nivelRepository.findAll();
    }

    @Override
    @Transactional
    public void eliminarNivel(Integer id) {
        nivelRepository.delete(findByIdNivel(id));
    }

    @Override
    @Transactional
    public void crearNivel(Nivel nivel) {
        nivelRepository.save(nivel);
    }

    @Override
    @Transactional(readOnly = true)
    public Nivel findByIdNivel(Integer id) {
        return nivelRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Nivel buscarPorNombreNivel(String nombre) {
        return nivelRepository.findByNombre(nombre).orElse(null);
    }
    
}
