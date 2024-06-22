package com.proyect.ecommerce.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "detalle")
public class DetalleOrden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    public String nombre;
    private Integer cantidad;
    private  Double precio;
    private Double total;

    @OneToOne
    private Orden orden;

    @ManyToOne
    private Producto producto;


}
