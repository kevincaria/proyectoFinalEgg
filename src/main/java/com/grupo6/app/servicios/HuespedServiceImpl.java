package com.grupo6.app.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.grupo6.app.entidades.Huesped;
import com.grupo6.app.repositorios.HuespedRepository;

@Service
public class HuespedServiceImpl implements HuespedService{

    @Autowired
    private HuespedRepository huespedRepository;
    
    @Override
    @Transactional(readOnly = true)
    public List<Huesped> listarHuespedes() {
        return huespedRepository.findAll();
    }

    @Override
    @Transactional
    public void eliminarHuesped(Integer id) {
        huespedRepository.delete(findByIdHuesped(id));
    }

    @Override
    @Transactional
    public void crearHuesped(Huesped huesped) {
        huespedRepository.save(huesped);
    }

    @Override
    @Transactional(readOnly = true)
    public Huesped findByIdHuesped(Integer id) {
        return huespedRepository.findById(id).orElse(null);
    }

}
