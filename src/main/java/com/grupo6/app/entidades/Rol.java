package com.grupo6.app.entidades;

import com.grupo6.app.enums.RolNombre;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "roles")
public class Rol implements Serializable {

    private static final long serialVersionUID = 6565314454241232618L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rol_id", nullable = false)
    private Integer id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private RolNombre rolNombre;

}