package com.grupo6.app.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.grupo6.app.entidades.Empleado;
import com.grupo6.app.repositorios.EmpleadoRepository;

@Service
public class EmpleadoServiceImpl implements EmpleadoService{

    @Autowired
    private EmpleadoRepository empleadoRepository;
    
    @Override
    @Transactional(readOnly = true)
    public List<Empleado> listarEmpleados() {
        return empleadoRepository.findAll();
    }

    @Override
    @Transactional
    public void eliminarEmpleado(Integer id) {
        empleadoRepository.delete(findByIdEmpleado(id));
    }

    @Override
    @Transactional
    public void crearEmpleado(Empleado empleado) {
        empleadoRepository.save(empleado);
    }

    @Override
    @Transactional(readOnly = true)
    public Empleado findByIdEmpleado(Integer id) {
        return empleadoRepository.findById(id).orElse(null);
    }

}
