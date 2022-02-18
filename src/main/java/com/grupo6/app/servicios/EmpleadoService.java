package com.grupo6.app.servicios;

import java.util.List;
import com.grupo6.app.entidades.Empleado;

public interface EmpleadoService {
    public List<Empleado> listarEmpleados();
    public void eliminarEmpleado(Integer id);
    public void crearEmpleado(Empleado Empleado);
    public Empleado findByIdEmpleado(Integer id);
}
