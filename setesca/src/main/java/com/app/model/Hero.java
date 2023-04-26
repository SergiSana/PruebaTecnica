package com.app.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="hero")
public class Hero implements Serializable{

	private static final long serialVersionUID = -8858384893983057127L;

	public Hero(Integer id, String nombre, String otros) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.otros = otros;
	}
	
	public Hero() {
		
	}
	
	@Id
	@Column(name="id")
	private Integer id;


	@Column(name="nombre")
	private String nombre;
	
	@Column(name="otros")
	private String otros;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getOtros() {
		return otros;
	}

	public void setOtros(String otros) {
		this.otros = otros;
	}
 
}
