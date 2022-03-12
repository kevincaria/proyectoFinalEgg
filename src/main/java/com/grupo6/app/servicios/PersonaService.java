package com.grupo6.app.servicios;

import java.util.List;

import com.grupo6.app.entidades.Persona;

public interface PersonaService {
    List<Persona> listarPersonas();

    void eliminarDniPersona(String dni);

    void crearPersona(Persona Persona);

    Persona buscarIdPersona(Integer id);

    Persona buscarDniPersona(String dni);
}
