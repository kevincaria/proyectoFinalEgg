package com.grupo6.app.servicios;

import java.util.List;
import com.grupo6.app.entidades.Persona;

public interface PersonaService {
    public List<Persona> listarPersonas();
    public void eliminarPersona(Integer id);
    public void crearPersona(Persona Persona);
    public Persona findByIdPersona(Integer id);
    public Persona buscarPorNombrePersona(String nombre);
}
