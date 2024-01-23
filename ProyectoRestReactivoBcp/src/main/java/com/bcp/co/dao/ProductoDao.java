package com.bcp.co.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.bcp.co.document.Producto;


public interface ProductoDao extends ReactiveMongoRepository<Producto, String>{

}
