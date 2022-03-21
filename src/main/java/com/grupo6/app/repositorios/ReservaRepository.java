package com.grupo6.app.repositorios;

import com.grupo6.app.entidades.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.grupo6.app.entidades.Reserva;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer> {

//    @Query("SELECT r FROM Reserva r WHERE r.fechaIngreso =:i and r.fechaSalida =:s ")
//    List<Reserva> findAllFechasIngresoSalida (@Param("i") LocalDate inicio, @Param("s") LocalDate salida);

//    @Query("SELECT r FROM Reserva r " +
//            "WHERE (r.fechaIngreso < :i and r.fechaSalida > :i )" +
//            "or (r.fechaIngreso < :s and r.fechaSalida > :s)" +
//            "or(:i BETWEEN r.fechaIngreso and r.fechaSalida and :s BETWEEN r.fechaIngreso and r.fechaSalida)" +
//            "or(r.fechaIngreso <= :i and r.fechaSalida >= :s)")
//    List<Reserva> findAllFechasIngresoSalida (@Param("i") LocalDate inicio, @Param("s") LocalDate salida);

    @Query("SELECT h " +
            "FROM Habitacion h " +
            "WHERE h.categoria.cantidad = :cantidad AND h " +
            "NOT IN(SELECT r.habitacion FROM Reserva r " +
            "WHERE (r.fechaIngreso < :i and r.fechaSalida > :i )" +
            "or (r.fechaIngreso < :s and r.fechaSalida > :s)" +
            "or(:i BETWEEN r.fechaIngreso and r.fechaSalida and :s BETWEEN r.fechaIngreso and r.fechaSalida)" +
            "or(r.fechaIngreso <= :i and r.fechaSalida >= :s))")
    List<Habitacion> findAllFechasIngresoSalidaCantidad (@Param("i") LocalDate inicio,
                                                         @Param("s") LocalDate salida,
                                                         @Param("cantidad")Integer cantidadPersonas);

    @Query("SELECT r FROM Reserva r WHERE " +
//            "r.fechaIngreso LIKE :q " +
//            "or r.fechaSalida LIKE :q " +
            "r.habitacion.nombre LIKE :q " +
            "or r.cliente.persona.dni LIKE :q " +
            "or r.cliente.persona.nombre LIKE :q " +
            "or r.cliente.persona.apellido LIKE :q")
    List<Reserva> findAllByQ(@Param("q")String q);


//    select habitacion_id
//    from habitacion
//    where categoria_id = 2 and habitacion_id not in (
//            select id_habitacion
//from reserva
//        where (fecha_ingreso < @fec_entrada and fecha_salida > @fec_entrada)
//    or (fecha_ingreso < @fec_salida and fecha_salida > @fec_salida)
//    or (@fec_entrada between fecha_ingreso and fecha_salida and @fec_salida between fecha_ingreso and fecha_salida)
//    or (fecha_ingreso <=@fec_entrada and fecha_salida >= @fec_salida)
//)
}