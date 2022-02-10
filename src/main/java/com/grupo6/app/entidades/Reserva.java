package com.grupo6.app.entidades;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "reserva")
public class Reserva implements Serializable {

    private static final long serialVersionUID = 8154277958314205310L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reserva_id", nullable = false)
    private Integer id;

    @NotNull(message = "Debe marcar una fecha de ingreso")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "fecha_ingreso", nullable = false)
    private LocalDate fechaIngreso;

    @NotNull(message = "Debe marcar una fecha de egreso")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "fecha_salida", nullable = false)
    private LocalDate fechaSalida;

    @NotNull(message = "Debe ingresar la cantidad de personas")
    @Column(name = "cantidad_personas", nullable = false)
    private Integer cantidadPersonas;

    @Column(name = "alta")
    private Boolean alta;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name = "habitacion_id")
    private Habitacion habitacion;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name = "empleado_id")
    private Empleado empleado;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name = "pago_id")
    private Pago pago;



}