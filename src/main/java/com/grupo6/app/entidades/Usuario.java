package com.grupo6.app.entidades;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = -232498464754500196L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id", nullable = false)
    private Integer id;

    @NotEmpty(message = "Debe ingrese un nombre de usuario")
    @Size(min = 6,max = 10, message = "Debe ingresar entre 6 y 10 caracteres")
    @Column(name = "username", nullable = false, length = 100)
    private String username;

    @NotEmpty(message = "El password no debes estar vacio")
    @Column(name = "password", nullable = false, length = 128)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_roles",joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"usuario_id","rol_id"})})
    private List<Rol> roles = new ArrayList<>();

}