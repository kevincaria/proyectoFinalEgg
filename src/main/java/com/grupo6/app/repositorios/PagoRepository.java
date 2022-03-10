package com.grupo6.app.repositorios;

import com.grupo6.app.entidades.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface PagoRepository extends JpaRepository<Pago, Integer> {
}
