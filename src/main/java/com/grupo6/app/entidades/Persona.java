package com.grupo6.app.entidades;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "persona")
public class Persona implements Serializable {

    private static final long serialVersionUID = -6443854902331297206L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "persona_id", nullable = false)
    private Integer id;

    @NotEmpty(message = "El nombre no puede estar vacio")
    @Size(min = 3, max = 100, message = "El nombre tiene que tener min 3 caracteres")
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @NotEmpty(message = "El apellido no puede estar vacio")
    @Column(name = "apellido", nullable = false, length = 100)
    private String apellido;

    @NotEmpty(message = "El Din no puede estar vacio")
    @Column(name = "dni", unique = true, nullable = false, length = 50)
    private String dni;

    @Column(name = "direccion", nullable = false, length = 100)
    private String direccion;

    @Column(name = "tel", length = 100)
    private String tel;

    @Email(message = "Debe ingresar un formato correcto de mail")
    @Column(name = "mail", length = 45)
    private String mail;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "fecha_nacimiento", nullable = false)
    private Date fechaNacimiento;

    @OneToMany(mappedBy = "persona")
    private List<Empleado> empleado;

    @OneToMany(mappedBy = "persona")
    private List<Cliente> cliente;

    @OneToMany(mappedBy = "persona")
    private List<Huesped> huesped;



}