package com.grupo6.app.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.grupo6.app.entidades.Pago;
import com.grupo6.app.repositorios.PagoRepository;

@Service
public class PagoServiceImpl implements PagoService{

    @Autowired
    private PagoRepository pagoRepository;
    
    @Override
    @Transactional(readOnly = true)
    public List<Pago> listarPagos() {
        return pagoRepository.findAll();
    }

    @Override
    @Transactional
    public void eliminarPago(Integer id) {
        pagoRepository.delete(findByIdPago(id));
    }

    @Override
    @Transactional
    public void crearPago(Pago pago) {
        pagoRepository.save(pago);
    }

    @Override
    @Transactional(readOnly = true)
    public Pago findByIdPago(Integer id) {
        return pagoRepository.findById(id).orElse(null);
    }

}
