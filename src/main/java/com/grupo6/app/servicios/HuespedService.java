package com.grupo6.app.servicios;

import java.util.List;
import com.grupo6.app.entidades.Huesped;

public interface HuespedService {
    public List<Huesped> listarHuespedes();
    public void eliminarHuesped(Integer id);
    public void crearHuesped(Huesped Huesped);
    public Huesped findByIdHuesped(Integer id);
}
