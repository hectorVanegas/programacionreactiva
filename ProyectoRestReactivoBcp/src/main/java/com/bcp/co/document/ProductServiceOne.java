package com.bcp.co.document;

import java.io.Serializable;
import java.util.Date;


import org.springframework.stereotype.Component;

@Component
public class ProductServiceOne implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String nombre;
	
	private Double precio;
	
	private Date createAt;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
	
	
	
}
