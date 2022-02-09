package com.grupo6.app.entidades;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "nivel")
public class Nivel implements Serializable {

    private static final long serialVersionUID = -4737123899238911422L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nivel", nullable = false)
    private Integer id;

    private String nombre;

}