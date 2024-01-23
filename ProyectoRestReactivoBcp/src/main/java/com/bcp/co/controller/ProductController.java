package com.bcp.co.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Date;
import com.bcp.co.document.Producto;
import com.bcp.co.service.ProductoService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/product")
public class ProductController {

	@Autowired
	private ProductoService service;
	
	@GetMapping
	public Mono<ResponseEntity<Flux<Producto>>> listarProductos(){
		
		return Mono.just(
			ResponseEntity.ok()
			.contentType(MediaType.APPLICATION_JSON_UTF8)
			.body(service.findAll())
				);
	}
	
	@GetMapping ("/{id}")
	public Mono<ResponseEntity<Producto>> verDetalle(@PathVariable String id){
		
		return service.findById(id)
			.map(p -> ResponseEntity.ok()
			.contentType(MediaType.APPLICATION_JSON_UTF8)
			.body(p))
			.defaultIfEmpty(ResponseEntity.noContent().build());
	}
	
	@GetMapping ("/name")
	public Mono<ResponseEntity<Flux<Producto>>> productosConMayuscula(@PathVariable String id){
		
		
		return Mono.just(
				ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(service.findAllConNombreUpperCase())
					);
	}
	
	@PostMapping
	public Mono<ResponseEntity<Producto>> crearProducto(@RequestBody Producto producto){
		
		if(producto.getCreateAt() == null) {
			producto.setCreateAt(new Date());
		}
		return service.save(producto).map(p -> ResponseEntity.created(URI.create("/api/product".concat(p.getId())))
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(p));	
	}
	
	@PutMapping ("/{id}")
	public Mono<ResponseEntity<Producto>> actualizarProducto(@RequestBody Producto producto, @PathVariable String id){
		
		return service.findById(id).flatMap(p ->{
			p.setNombre(producto.getNombre());
			p.setPrecio(producto.getPrecio());
			p.setCategoria(producto.getCategoria());
			
			return service.save(p);
			
		}).map(p -> ResponseEntity.created(URI.create("/api/product".concat(p.getId())))
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(p)).defaultIfEmpty(ResponseEntity.noContent().build());
		
	}
	
	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<Producto>> eliminarProducto(@PathVariable String id){
		
		return service.findById(id).flatMap(p -> {
			return service.delete(p).then(Mono.just(ResponseEntity.noContent().build()));
		});
	}
	
	@GetMapping("/merge")
	public Mono<ResponseEntity<Flux<Object>>> paralell(){
		
		return Mono.just(
			ResponseEntity.ok()
			.contentType(MediaType.APPLICATION_JSON_UTF8)
			.body(service.fetchOneAndTwoService()));
	}
	
}
