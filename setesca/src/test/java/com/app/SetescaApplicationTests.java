package com.app;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.app.controller.HeroController;
import com.app.controller.ResponseGeneral;
import com.app.model.Hero;

@SpringBootTest
class SetescaApplicationTests {

	@Autowired
	private HeroController heroController;

	/**
	 * Validar que se añaden heroes y la lista obtenida cooincide con el numero insertado.
	 */
	@Test
	void obtenerHeroes() {
		try {
			ResponseEntity<ResponseGeneral> resp = heroController.obtenerHeroes();
			@SuppressWarnings("unchecked")
			List<Hero> lista = (List<Hero>)resp.getBody().getObjeto();
			assertThat(6).isEqualTo(lista.size());
		}
		catch (Exception e) {
			assert(false);
		}
	}
	
	/**
	 * Validar que se puede añadir un heroe.
	 */
	@Test
	void añadirHeroe() {
		try {
			ResponseEntity<ResponseGeneral> resp = heroController.obtenerHeroes("25");
			Hero he1 = (Hero) resp.getBody().getObjeto();
			if(null==he1) {
				//No existe aun
				he1 = new Hero(25,"Nuevo","Otros");
				heroController.anyadirHeroe(he1);
				
				resp = heroController.obtenerHeroes("25");
				Hero he2 = (Hero) resp.getBody().getObjeto();
				assertThat(he1.getNombre()).isEqualTo(he2.getNombre());
			}
			
			
		}
		catch (Exception e) {
			assert(false);
		}
	}
	
	/**
	 * Dado un id, se obtiene de bbdd.
	 */
	@Test
	void obtenerHeroePorId() {
		try {
			ResponseEntity<ResponseGeneral> resp = heroController.obtenerHeroes("2");
			Hero he = (Hero) resp.getBody().getObjeto();
			assert(he.getNombre().equals("Superman"));
		}
		catch (Exception e) {
			assert(false);
		}
	}
	
	/**
	 * Validar que se puede modificar un heroe existente.
	 */
	@Test 
	void modificarHeroe() {
		try {
			
			Hero he = new Hero(1,"Spiderman","Lanza redes");
			heroController.modificarHeroe(he);
			ResponseEntity<ResponseGeneral> resp = heroController.obtenerHeroes("1");
			Hero he2 = (Hero) resp.getBody().getObjeto();
			assertThat(he2.getOtros()).isEqualTo(he.getOtros());
		}
		catch (Exception e) {
			assert(false);
		}
	}

	/**
	 * Dado un patron, se obtienen los heroes que lo contienen en el nombre.
	 */
	@Test
	void obtenerHeroesPorPatron() {
		try {
			ResponseEntity<ResponseGeneral> resp = heroController.contienenPatron("man");
			@SuppressWarnings("unchecked")
			List<Hero> lista = (List<Hero>) resp.getBody().getObjeto();
			assertThat(3).isEqualTo(lista.size());
		}
		catch (Exception e) {
			assert(false);
		}
	}
	
	/**
	 * Despues de validar que hay un heroe añadido, se valida que se borra correctamente.
	 */
	@Test
	void borrarHeroe() {

		try {
			ResponseEntity<ResponseGeneral> resp = heroController.obtenerHeroes("1");
			Hero he = (Hero) resp.getBody().getObjeto();
			if(null!=he) {
				//Hay una coincidencia
				heroController.borrarHeroe("1");
				resp = heroController.obtenerHeroes("1");
				he = (Hero) resp.getBody().getObjeto();
				assertNull(he);
			}
		}
		catch (Exception e) {
			assert(false);
		}
	}
}
