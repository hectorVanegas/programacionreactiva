package com.bcp.co.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.bcp.co.document.Categoria;


public interface CategoriaDao extends ReactiveMongoRepository<Categoria, String>{

}
