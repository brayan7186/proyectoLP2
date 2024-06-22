package com.proyect.ecommerce.service;

import com.proyect.ecommerce.model.Usuario;

import java.util.Optional;

public interface IUsuarioService {
      Optional<Usuario> findByID(Integer  id);



}
