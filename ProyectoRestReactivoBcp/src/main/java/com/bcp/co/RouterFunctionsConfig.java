package com.bcp.co;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import org.springframework.web.reactive.function.server.RouterFunction;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.bcp.co.controller.ProductHandlerController;
import com.bcp.co.document.Producto;
import com.bcp.co.service.ProductoService;

@Configuration
public class RouterFunctionsConfig {
	
	
	
	@Bean
	public RouterFunction<ServerResponse> routes (ProductHandlerController handler){
		
		return route(GET("/api/v1/product").or(GET("/api/v2/product")), handler::listar)
				.andRoute(GET("/api/v1/product/{id}").or(GET("/api/v2/product/{id}")),handler::verProducto)
				.andRoute(POST("/api/v1/product"), handler::guardarProducto)
				.andRoute(PUT("/api/v1/product"), handler::actualizarProducto)
				.andRoute(DELETE("/api/v1/product"), handler::eliminarProducto);
			
	}

}
