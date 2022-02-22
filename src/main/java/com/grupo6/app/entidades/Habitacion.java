package com.grupo6.app.entidades;

import com.grupo6.app.enums.Estado;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "habitacion")
public class Habitacion implements Serializable {

    private static final long serialVersionUID = 2567821086822562978L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "habitacion_id", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 10)
    private String nombre;

    @Column(name = "descripcion", nullable = false, length = 200)
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private Estado estado;

    @Column(name = "precio")
    private Double precio;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name = "nivel_id")
    private Nivel nivel;

    @OneToMany(mappedBy = "habitacion")
    private List<Reserva> reserva;

}