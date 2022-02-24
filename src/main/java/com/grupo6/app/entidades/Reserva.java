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

<<<<<<< ours
=======
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Integer getCantidadPersonas() {
        return cantidadPersonas;
    }

    public void setCantidadPersonas(Integer cantidadPersonas) {
        this.cantidadPersonas = cantidadPersonas;
    }

    public Boolean getAlta() {
        return alta;
    }

    public void setAlta(Boolean alta) {
        this.alta = alta;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "id=" + id +
                ", fechaIngreso=" + fechaIngreso +
                ", fechaSalida=" + fechaSalida +
                ", cantidadPersonas=" + cantidadPersonas +
                ", alta='" + alta + '\'' +
                ", habitacion=" + habitacion +
                ", cliente=" + cliente +
                ", empleado=" + empleado +
                ", pago=" + pago +
                '}';
    }
>>>>>>> theirs
}