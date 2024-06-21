package com.proyect.ecommerce.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String descripcion;
    private String imagen;
    private Double precio;
    private Integer cantidad;


    @ManyToOne
    @NonNull
    private Usuario usuario;


    @Override
    public String toString() {
        return "Producto [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", imagen=" + imagen
                + ", precio=" + precio + ", cantidad=" + cantidad + "]";
    }
}

