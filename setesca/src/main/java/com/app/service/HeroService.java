package com.app.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.controller.ResponseGeneral;
import com.app.model.Hero;
import com.app.repository.HeroRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.micrometer.common.util.StringUtils;

@Service
public class HeroService {

	Logger logger = LoggerFactory.getLogger(HeroService.class);
	
	@Autowired HeroRepository heroRepository;
	@Autowired ObjectMapper oMap;
	
	/**
	 * Dado un id de heroe, lo busca en bbdd y lo retorna.
	 * @param id El id del heroe
	 * @return El heroe encontrado
	 */
	public ResponseGeneral obtenerHeroe(String id) {
		logger.info("Inicio obtenerHeroe: " + id) ;
		ResponseGeneral respuesta = new ResponseGeneral();
		Hero heroe = null;
		try {
			if(StringUtils.isNotEmpty(id)) {
				Optional<Hero> he = heroRepository.findById(Integer.valueOf(id));
				if(he.isPresent()) {
					heroe = he.get();
					respuesta.setResultado("OK");
					respuesta.setObjeto(heroe);
				}
			}
		}
		catch (Exception e) {
			logger.error(e.getMessage());
			respuesta.setResultado("KO");
			respuesta.setError("Excepcion al obtener el heroe indicado.");
		}
		finally {
			logger.info("Fin obtenerHeroe");
		}
		return respuesta;
	}
	
	/**
	 * Obtiene la lista de heroes en bbdd.
	 * 
	 * @return La lista de heroes encontrados.
	 */
	public ResponseGeneral obtenerHeroes() {
		logger.info("Inicio obtenerHeroes") ;
		ResponseGeneral respuesta = new ResponseGeneral();
		
		List<Hero> lista = null;
		try {
			lista = new ArrayList<>();
			Iterable<Hero> itHero = heroRepository.findAll();
			Iterator<Hero> it = itHero.iterator();
			while(it.hasNext()) {
				lista.add(it.next());
			}
			respuesta.setObjeto(lista);
			respuesta.setResultado("OK");
		}
		catch (Exception e) {
			logger.error(e.getMessage());
			respuesta.setResultado("KO");
			respuesta.setError("Excepcion buscando la lista de heroes: ");
		}
		finally {
			logger.info("Fin obtenerHeroes") ;
		}
		return respuesta;
	}
	
	/**
	 * Añade un heroe a bbdd.
	 * 
	 * @param heroe Los datos del heroe. 
	 * @return Resultado de la operacion.
	 */
	public ResponseGeneral anyadirHeroe(Hero heroe) {

		ResponseGeneral respuesta = null;
		
		try {
			logger.info("Inicio anyadirHeroe: " + oMap.writer().withDefaultPrettyPrinter().writeValueAsString(heroe));
			respuesta = new ResponseGeneral();

			if(heroRepository.existsById(heroe.getId())) {
				respuesta.setError("Ya existe ese id de heroe");
				respuesta.setResultado("KO");
			}
			else{
				heroRepository.save(heroe);
				respuesta.setResultado("OK");
				respuesta.setObjeto("Añadido correctamente: " + heroe.getNombre());
			}
		}
		catch (Exception e) {
			respuesta.setResultado("KO");
			respuesta.setError("Excepcion al añadir el hero. ");
			logger.error(e.getMessage());
		}
		finally {
			logger.info("Fin anyadirHeroe");
		}
		return respuesta;
	}
	
	/**
	 * Dado un id, lo borra de bbdd si existe.
	 * 
	 * @param id id del heroe a borrar. 
	 * @return El resultado de la operacion.
	 */
	public ResponseGeneral borrarHeroe(String id) {
		logger.info("Inicio borrarHeroe: " + id) ;
		ResponseGeneral respuesta = new ResponseGeneral();
		
		try {
			if(!StringUtils.isEmpty(id)) {
				Hero heroe = new Hero();
				heroe.setId(Integer.valueOf(id));
				if(heroRepository.existsById(Integer.valueOf(id))) {
					heroRepository.delete(heroe);
					respuesta.setObjeto("Borrado correctamente heroe con id: " + id);
					respuesta.setResultado("OK");
				}
				else {
					respuesta.setError("No existe el heroe");
					respuesta.setResultado("KO");
				}
			}
			else {
				respuesta.setError("Falta el heroe que borrar.");
				respuesta.setResultado("KO");
			}
		}
		catch (Exception e) {
			logger.error("Excepcion al borrar el heroe: " + e.getMessage());
			respuesta.setResultado("KO");
			respuesta.setError("Excepcion al borrar el heroe.");
		}
		finally {
			logger.info("Fin borrarHeroe") ;
		}
		return respuesta;
	}
	
	/**
	 * Dado un patron, retornara aquellos nombres que lo contengan.
	 * 
	 * @param patron Patron a buscar.
	 * @return Los heroes encontrados.
	 */
	public ResponseGeneral obtenerPorPatron(String patron) {
		logger.info("Inicio obtenerPorPatron: " + patron) ;
		ResponseGeneral respuesta = new ResponseGeneral();
		List<Hero> lista = null;
		try {
			if(!StringUtils.isEmpty(patron)) {
				lista = heroRepository.buscarPorPatron(patron);
				respuesta.setObjeto(lista);
			}
		}
		catch (Exception e) {
			logger.error("Excepcion buscar por patron: " + e.getMessage());
			respuesta.setError("Excepcion buscar por patron: " + e.getMessage());
			respuesta.setResultado("KO"); 
		}
		finally {
			logger.info("Fin obtenerPorPatron") ;
		}
		return respuesta;
	}
	
	/**
	 * Dado un id de heroe, lo modifica por los datos indicados.
	 * 
	 * @param hero El heroe con el id como minimo.
	 * @return El resultado de la operacion.
	 */
	public ResponseGeneral modificarHeroe(Hero hero){
		ResponseGeneral respuesta = null;
		try {
			logger.info("Inicio modificarHeroe: " + oMap.writer().withDefaultPrettyPrinter().writeValueAsString(hero));
			
			respuesta = new ResponseGeneral();
			if(null!=hero && null!=hero.getId()) {
				if(heroRepository.existsById(hero.getId())) {
					heroRepository.save(hero);
					respuesta.setResultado("OK"); 
					respuesta.setObjeto("Modificacion correcta.");
				}
				else {
					respuesta.setError("No existe el heroe a modificar con id: " + hero.getId());
					respuesta.setResultado("KO"); 
				}
			}
			else {
				respuesta.setError("Es necesario informar como minimo un id.");
				respuesta.setResultado("KO"); 
			}
		}
		catch (Exception e) {
			logger.error("Excepcion al modificar el heroe: " + e.getMessage());
			respuesta.setError("Excepcion al modificar el heroe: " + e.getMessage());
			respuesta.setResultado("KO"); 
		}
		finally {
			logger.info("Fin modificarHeroe") ;
		}
		return respuesta;
	}
}
