package com.proyect.ecommerce.service.impl;

import com.proyect.ecommerce.model.DetalleOrden;
import com.proyect.ecommerce.repository.IDetalleOrdenRepository;
import com.proyect.ecommerce.service.IDetalleOrdenService;
import org.springframework.beans.factory.annotation.Autowired;

public class DetalleOrdenServiceImpl  implements IDetalleOrdenService {
    @Autowired
    private IDetalleOrdenRepository detalleOrdenRepository;

    @Override
    public DetalleOrden save(DetalleOrden detalleOrden) {
        return detalleOrdenRepository.save(detalleOrden);
    }
}
