package com.grupo6.app.entidades;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "huesped")
public class Huesped implements Serializable {

    private static final long serialVersionUID = 4308689895597444795L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "huesped_id", nullable = false)
    private Integer id;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name = "reserva_id")
    private Reserva idReserva;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name = "persona_id")
    private Persona persona;

    @Column(name = "genero", length = 45)
    private String genero;


}