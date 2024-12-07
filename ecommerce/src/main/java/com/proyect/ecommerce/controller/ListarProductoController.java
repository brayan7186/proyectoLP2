package com.proyect.ecommerce.controller;

import com.proyect.ecommerce.model.Producto;
import com.proyect.ecommerce.service.IProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RequestMapping("/listar")
@RestController

public class ListarProductoController {

    private final Logger logger = LoggerFactory.getLogger(ProductoController.class);


    @Autowired
    private IProductoService productoService;

    @GetMapping("/producto")
    public List<Producto> listarProductos() {
        logger.info("Listando todos los productos");
        return productoService.FindAll();
    }
}
