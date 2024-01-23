package com.bcp.co.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import static org.springframework.web.reactive.function.BodyInserters.*;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import java.net.URI;
import com.bcp.co.document.Producto;
import com.bcp.co.service.ProductoService;

import reactor.core.publisher.Mono;

@Component
public class ProductHandlerController {

	@Autowired
	private ProductoService service;
	
	public Mono<ServerResponse> listar(ServerRequest request) {
		
		return ServerResponse
				.ok()
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(service.findAll(),Producto.class);
		
	}
	
	public Mono<ServerResponse> verProducto(ServerRequest request){
	
		String id = request.pathVariable("id");
		
		return service.findById(id).flatMap(p -> 
			ServerResponse
			.ok()
			.contentType(MediaType.APPLICATION_JSON_UTF8)
			.body(fromObject(p)))
			.switchIfEmpty(ServerResponse.noContent().build());
	
				
	}
	public Mono<ServerResponse> guardarProducto(ServerRequest request){
		
		Mono<Producto> product = request.bodyToMono(Producto.class);
		
		return product.flatMap(p -> {
			
			if(p.getCreateAt() == null) {
				p.setCreateAt(new Date());
			}
			return service.save(p);
			
		}).flatMap(p -> ServerResponse
				.created(URI.create("/api/v1/product/".concat(p.getId())))
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(fromObject(p)));
	}
	
	public Mono<ServerResponse> actualizarProducto(ServerRequest request){
		
		Mono<Producto> product = request.bodyToMono(Producto.class);
		String id = request.pathVariable("id");
		
		
		Mono<Producto> productDb = service.findById(id);
		
		return productDb.zipWith(product, (bd , req) ->{
			
		bd.setNombre(req.getNombre());
		bd.setPrecio(req.getPrecio());
		bd.setCategoria(req.getCategoria());
				
			return bd;
		}).flatMap(p -> ServerResponse
				.created(URI.create("/api/v1/product/".concat(p.getId())))
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(fromObject(p)))	
				.switchIfEmpty(ServerResponse.noContent().build());
		
	}
	
	public Mono<ServerResponse> eliminarProducto(ServerRequest request){
		
		String id = request.pathVariable("id");
		Mono<Producto> productDb = service.findById(id);
		
		return productDb.flatMap(p -> service.delete(p).then(ServerResponse.noContent().build()))
				.switchIfEmpty(ServerResponse.notFound().build());
		
	}
	
	
}
