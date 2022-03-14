package com.grupo6.app.servicios;

import java.util.List;

import com.grupo6.app.entidades.Cliente;

public interface ClienteService {
     List<Cliente> listarClientes();

     void eliminarCliente(Integer id);

     void crearCliente(Cliente Cliente);

     Cliente findByIdCliente(Integer id);

    Cliente buscarClientePorIdPersona(Integer idPersona);

}
