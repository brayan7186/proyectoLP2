package com.proyect.ecommerce.service.impl;

import com.proyect.ecommerce.model.Orden;
import com.proyect.ecommerce.repository.IOrdenRepository;
import com.proyect.ecommerce.service.IOrdenService;
import org.springframework.beans.factory.annotation.Autowired;

public class OrdenSeriviceImpl implements IOrdenService {
    @Autowired
    private IOrdenRepository ordenRepository;

    @Override
    public Orden save(Orden orden) {
        return ordenRepository.save(orden);
    }
}
