package com.grupo6.app.servicios;

import java.util.List;
import com.grupo6.app.entidades.Cliente;

public interface ClienteService {
    public List<Cliente> listarClientes();
    public void eliminarCliente(Integer id);
    public void crearCliente(Cliente Cliente);
    public Cliente findByIdCliente(Integer id);
    public Cliente buscarPorNombreCliente(String nombre);
}
