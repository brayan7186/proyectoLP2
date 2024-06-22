package com.proyect.ecommerce.service.impl;

import com.proyect.ecommerce.model.Usuario;
import com.proyect.ecommerce.repository.IUsuarioRepository;
import com.proyect.ecommerce.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl  implements IUsuarioService {


     @Autowired
     private IUsuarioRepository iUsuarioRepository;


    @Override
    public Optional<Usuario> findByID(Integer id) {

        return  iUsuarioRepository.findById(id) ;

    }

}
