package com.app.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.controller.ResponseGeneral;
import com.app.model.Animal;
import com.app.repository.AnimalRepository;

@Service
public class AnimalService {

	Logger logger = LoggerFactory.getLogger(AnimalService.class);
	
	@Autowired AnimalRepository animalRepository;
	
	/**
	 * Retorna los animales existentes.
	 * 
	 * @return La lista de animales.
	 */
	public ResponseGeneral listaAnimales(){
		ResponseGeneral respuesta = new ResponseGeneral();
		
		List<Animal> lista = null;
		
		try {
			lista = new ArrayList<>();
			Iterable<Animal> itList = animalRepository.findAll();
			Iterator<Animal> it = itList.iterator();
			while(it.hasNext()) {
				lista.add(it.next());
			}
			respuesta.setObjeto(lista);
			respuesta.setResultado("OK");
		}
		catch (Exception e) {
			logger.error(e.getMessage());
			respuesta.setResultado("KO");
			respuesta.setError("Error al recuperar la lista.");
		}
		
		return respuesta;
	}
}
