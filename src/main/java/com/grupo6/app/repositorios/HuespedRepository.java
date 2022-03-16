package com.grupo6.app.repositorios;

import com.grupo6.app.entidades.Huesped;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface HuespedRepository extends JpaRepository<Huesped, Integer> {
}
