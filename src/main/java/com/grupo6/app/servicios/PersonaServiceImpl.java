package com.grupo6.app.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.grupo6.app.entidades.Persona;
import com.grupo6.app.repositorios.PersonaRepository;

@Service
public class PersonaServiceImpl implements PersonaService{

    @Autowired
    private PersonaRepository personaRepository;
    
    @Override
    @Transactional(readOnly = true)
    public List<Persona> listarPersonas() {
        return personaRepository.findAll();
    }

    @Override
    @Transactional
    public void eliminarPersona(Integer id) {
        personaRepository.delete(findByIdPersona(id));
    }

    @Override
    @Transactional
    public void crearPersona(Persona persona) {
        personaRepository.save(persona);
    }

    @Override
    @Transactional(readOnly = true)
    public Persona findByIdPersona(Integer id) {
        return personaRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Persona buscarPorNombrePersona(String nombre) {
        return personaRepository.findByNombrePersona(nombre).orElse(null);
    }
    
}
