package com.grupo6.app.entidades;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "empleado")
public class Empleado implements Serializable {

    private static final long serialVersionUID = -8398560431926672040L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "empleado_id", nullable = false)
    private Integer id;

    @Column(name = "departamento", nullable = false, length = 100)
    private String departamento;

    @Column(name = "alta")
    private Boolean alta;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name = "persona_id")
    private Persona persona;

    @OneToMany(mappedBy = "empleado")
    private List<Reserva> reserva;

}