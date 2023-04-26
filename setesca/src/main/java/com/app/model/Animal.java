package com.app.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="animal")
public class Animal implements Serializable{
	
	private static final long serialVersionUID = -6531760938588495698L;

	public Animal(Integer id, Integer id_heroe, String nombre) {
		super();
		this.id = id;
		this.id_heroe = id_heroe;
		this.nombre = nombre;
	}
	
	public Animal() {
	}			

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="id_heroe")
	private Integer id_heroe;
	
	@Column(name="nombre")
	private String nombre;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId_heroe() {
		return id_heroe;
	}

	public void setId_heroe(Integer id_heroe) {
		this.id_heroe = id_heroe;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
