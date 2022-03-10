package com.grupo6.app.repositorios;

import com.grupo6.app.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
