package com.bcp.co.service;

import java.time.Duration;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


import com.bcp.co.dao.CategoriaDao;
import com.bcp.co.dao.ProductoDao;
import com.bcp.co.document.Categoria;
import com.bcp.co.document.ProductServiceOne;
import com.bcp.co.document.ProductServiceTwo;
import com.bcp.co.document.Producto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductoServiceImpl implements ProductoService{

	@Autowired
	private ProductoDao dao;
	
	WebClient webClientOne = WebClient.create("http://localhost:8082");
	WebClient webClientTwo = WebClient.create("http://localhost:8083");
	
	
	@Autowired
	private CategoriaDao categoriaDao;
	
	@Override
	public Flux<Producto> findAll() {
		return dao.findAll();
	}

	@Override
	public Mono<Producto> findById(String id) {
		return dao.findById(id);
	}

	@Override
	public Mono<Producto> save(Producto producto) {
		return dao.save(producto);
	}

	@Override
	public Mono<Void> delete(Producto producto) {
		return dao.delete(producto);
	}

	@Override
	public Flux<Producto> findAllConNombreUpperCase() {
				
		return dao.findAll().map(producto -> {
			producto.setNombre(producto.getNombre().toUpperCase());
			return producto;
		});
	}

	@Override
	public Flux<Producto> findAllConNombreUpperCaseRepeat() {
		return findAllConNombreUpperCase().repeat(5000);
	}

	@Override
	public Flux<Categoria> findAllCategoria() {
		return categoriaDao.findAll();
	}

	@Override
	public Mono<Categoria> findCategoriaById(String id) {
		return categoriaDao.findById(id);
	}

	@Override
	public Mono<Categoria> saveCategoria(Categoria categoria) {
		return categoriaDao.save(categoria);
	}
	
	public Flux<Object> fetchOneAndTwoService() {
	    return Flux.merge(getCallServiceOne(), getCallServiceTwo());
	}
	
	public Mono<String> getCallServiceOne() {
	    return webClientOne.get()
	        .uri("/product/saludouno")
	        .retrieve()
	        .bodyToMono(String.class).delayElement(Duration.ofSeconds(4));
	}

	public Mono<String> getCallServiceTwo() {
	    
	    return webClientTwo.get()
	        .uri("/product/saludoDos")
	        .retrieve()
	        .bodyToMono(String.class);
	}

}
