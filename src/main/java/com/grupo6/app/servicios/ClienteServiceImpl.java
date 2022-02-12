package com.grupo6.app.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.grupo6.app.entidades.Cliente;
import com.grupo6.app.repositorios.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    
    @Override
    @Transactional(readOnly = true)
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    @Override
    @Transactional
    public void eliminarCliente(Integer id) {
        clienteRepository.delete(findByIdCliente(id));
    }

    @Override
    @Transactional
    public void crearCliente(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente findByIdCliente(Integer id) {
        return clienteRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente buscarPorNombreCliente(String nombre) {
        return clienteRepository.findByNombreCliente(nombre).orElse(null);
    }
    
}
