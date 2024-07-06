package com.proyect.ecommerce.service.impl;

import com.proyect.ecommerce.model.Orden;
import com.proyect.ecommerce.repository.IOrdenRepository;
import com.proyect.ecommerce.service.IOrdenService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class OrdenSeriviceImpl implements IOrdenService {
    @Autowired
    private IOrdenRepository ordenRepository;

    @Override
    public List<Orden> findAll() {
        return ordenRepository.findAll();
    }

    @Override
    public Orden save(Orden orden) {
        return ordenRepository.save(orden);
    }


    public String generarNumeroOrden() {
        int numer = 0;
        String numemorConcatenado = "";
        List<Orden> ordens = findAll();
        List<Integer> numeros = new ArrayList<Integer>();
        ordens.stream().forEach(o -> numeros.add(Integer.parseInt(o.getNumero())));


          if (ordens.isEmpty()){
              numer= 1;

          }else
          {
              numeros=numeros.stream().max(Integer::compare).get();
              numer++;
          }
        return  numemorConcatenado;
    }

















}
