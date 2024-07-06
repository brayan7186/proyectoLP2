package com.proyect.ecommerce.controller;

import com.proyect.ecommerce.model.DetalleOrden;
import com.proyect.ecommerce.model.Orden;
import com.proyect.ecommerce.model.Producto;
import com.proyect.ecommerce.model.Usuario;
import com.proyect.ecommerce.service.IDetalleOrdenService;
import com.proyect.ecommerce.service.IOrdenService;
import com.proyect.ecommerce.service.IProductoService;
import com.proyect.ecommerce.service.IUsuarioService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class HomeController {

    private final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private IProductoService productoService;

    @Autowired
    private IUsuarioService usuarioService;


    @Autowired
            private IOrdenService iOrdenService;
    @Autowired
            private IDetalleOrdenService iDetalleOrdenService;






    // para almacenar los detalles de la orden
    List<DetalleOrden> detalles = new ArrayList<DetalleOrden>();

    // datos de la orden
    Orden orden = new Orden();


    @GetMapping("")
    public String home(Model model, HttpSession session) {

        logger.info("Sesion del usuario: {}", session.getAttribute("idusuario"));
        model.addAttribute("productos", productoService.FindAll());
        //session
        model.addAttribute("sesion", session.getAttribute("idusuario"));
        return "usuario/home";
    }

    @GetMapping("productohome/{id}")
    public String productoHome(@PathVariable Integer id, Model model) {
        logger.info("Id producto enviado como parametreo {}", id);

        Producto producto = new Producto();
        Optional<Producto> productoOptional = productoService.get(id);
        producto = productoOptional.get();
        model.addAttribute("producto", producto);

        return "usuario/productohome";
    }

    @PostMapping("/cart")
    public String addCart(@RequestParam Integer id, @RequestParam Integer cantidad, Model model) {
        DetalleOrden detalleOrden = new DetalleOrden();
        Producto producto = new Producto();
        double sumaTotal = 0;
        Optional<Producto> optionalProducto = productoService.get(id);
        logger.info("Producto añadido : {} ", optionalProducto.get());
        logger.info("Cantidad : {}", cantidad);
        producto = optionalProducto.get();

        detalleOrden.setCantidad(cantidad);
        detalleOrden.setPrecio(producto.getPrecio());
        detalleOrden.setNombre(producto.getNombre());
        detalleOrden.setTotal(producto.getPrecio()* cantidad);
        detalleOrden.setProducto(producto);

        // validad que el producto no se añada dos veces
        Integer idProducto = producto.getId();
        boolean ingresado = detalles.stream().anyMatch(p -> p.getProducto().getId() == idProducto);

        if (!ingresado) {
            detalles.add(detalleOrden);
        }

        sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();
        orden.setTotal(sumaTotal);
        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);

        return "usuario/carrito";
    }

    // quitar un producto del carrito
    @GetMapping("/delete/cart/{id}")
    public String deleteProdutoCart(@PathVariable Integer id, Model model) {

        List<DetalleOrden> ordenesNuevo = new ArrayList<DetalleOrden>();
        for (DetalleOrden detalleOrden : detalles) {

            if (detalleOrden.getProducto().getId() != id) {
                ordenesNuevo.add(detalleOrden);
            }
        }

        // poner la nueva lista con prductos restantes
        detalles = ordenesNuevo;

        double sumaTotal = 0;
        sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();

        orden.setTotal(sumaTotal);
        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);

        return "usuario/carrito";
    }

    @GetMapping("/getCart")
    public String getCart(Model model) {

        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);


        return "/usuario/carrito";
    }

    @GetMapping("/order")
    public String order(Model model, HttpSession session) {

        Usuario usuario =usuarioService.findByID( Integer.parseInt(session.getAttribute("idusuario").toString())).get();

        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);
        model.addAttribute("usuario", usuario);

        return "usuario/resumenorden";
    }


    // guardar la orden
    @GetMapping("/saveOrder")
    public String saveOrder(HttpSession session ) {
        Date fechaCreacion = new Date();
        orden.setFechaCreacion(fechaCreacion);
        orden.setNumero(iOrdenService.generarNumeroOrden());

        //usuario
        Usuario usuario =usuarioService.findByID( Integer.parseInt(session.getAttribute("idusuario").toString())  ).get();

        orden.setUsuario(usuario);
        iOrdenService.save(orden);

        //guardar detalles
        for (DetalleOrden dt:detalles) {
            dt.setOrden(orden);
            iDetalleOrdenService.save(dt);
        }

        ///limpiar lista y orden
        orden = new Orden();
        detalles.clear();

        return "redirect:/";
    }



    @PostMapping("/search")
    public String searchProduct(@RequestParam String  nombre,Model model){
    logger.info("nombre del  producto : {}",nombre);
    List<Producto> productos = productoService.FindAll().stream().filter(p -> p.getNombre().contains(nombre)).collect(Collectors.toList());
    model.addAttribute("productos",productos);

    return "usuario/home";

       }

}
