package com.proyect.ecommerce.service;

import com.proyect.ecommerce.model.Orden;

import java.util.List;

public interface IOrdenService {
      List<Orden>  findAll();
    Orden save (Orden  orden);
}
