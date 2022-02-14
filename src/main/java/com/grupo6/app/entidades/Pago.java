package com.grupo6.app.entidades;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "pago")
public class Pago implements Serializable {

    private static final long serialVersionUID = -7271383503006207924L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pago_id", nullable = false)
    private Integer id;

    @Column(name = "comprobante_img")
    private String comprobanteImg;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "fecha_emision")
    private Date fechaEmision;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "fecha_pago")
    private Date fechaPago;

    @Column(name = "pago_total")
    private Double pagoTotal;

    @Column(name = "pago_parcial")
    private Double pagoParcial;

    @Column(name = "tipo_factura", length = 45)
    private String tipoFactura;

    @OneToMany(mappedBy = "pago")
    private List<Reserva> reserva;

}