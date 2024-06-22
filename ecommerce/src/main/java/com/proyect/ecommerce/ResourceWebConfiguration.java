package com.proyect.ecommerce;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourceWebConfiguration   implements WebMvcConfigurer {

    /* este método te permite servir recursos estáticos
     *  (como imágenes, archivos CSS, JavaScript, etc.) desde el
     *  sistema de archivos local en lugar de servirlos desde el
     *  contexto de la aplicación web, lo que puede ser útil para
     *  servir contenido estático que no necesita ser procesado
     *  dinámicamente por el servidor.
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/images/**").addResourceLocations("file:images/");
    }


	 /*<div class="col-lg-3 col-md-6 mb-4" th:each="producto: ${productos}">
     <div class="card h-100">
       <img class="card-img-top" th:src="@{/images/{img} (img=${producto.imagen})}" alt="">
       <div class="card-body">
          <p class="card-text" th:text="${producto.nombre}"></p>
       </div*/

}
