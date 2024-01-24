package com.bcp.co.document;


import java.io.Serializable;

import org.springframework.stereotype.Component;


@Component
public class ProductServiceTwo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Categoria categoria;
	
	private String foto;


	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	
	
	
}
