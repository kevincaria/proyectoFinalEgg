package com.grupo6.app.servicios;

import java.util.List;
import com.grupo6.app.entidades.Nivel;

public interface NivelService {
    public List<Nivel> listarNiveles();
    public void eliminarNivel(Integer id);
    public void crearNivel(Nivel Nivel);
    public Nivel findByIdNivel(Integer id);
    public Nivel buscarPorNombreNivel(String nombre);
}
