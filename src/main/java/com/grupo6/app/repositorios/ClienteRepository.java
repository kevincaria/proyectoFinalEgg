package com.grupo6.app.repositorios;

import com.grupo6.app.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
