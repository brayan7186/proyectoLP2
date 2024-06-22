package com.proyect.ecommerce.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @NonNull
    private Integer id;

    @NonNull
    private String nombre;

    @NonNull
    private String username;

    @NonNull
    private String email;

    @NonNull
    private String direccion;

    @NonNull
    private String telefono;

    @NonNull
    private String tipo;

    @NonNull
    private String password;

    @OneToMany(mappedBy = "usuario")
    private List<Producto> productos;

    @OneToMany(mappedBy = "usuario")
    private List<Orden> ordenes;

}