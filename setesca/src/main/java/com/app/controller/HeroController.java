package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Hero;
import com.app.service.HeroService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(path = "he")
public class HeroController {

	@Autowired HeroService heroService;

	@RequestMapping("/heroes")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",description = "operacion OK", content = @Content(schema = @Schema(implementation = ResponseGeneral.class))),
			@ApiResponse(responseCode = "400", description = "operacion KO")
	})
	public ResponseEntity<ResponseGeneral> obtenerHeroes() {
		return ResponseEntity.ok(heroService.obtenerHeroes());
	}
	
	@RequestMapping("/heroe")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",description = "operacion OK", content = @Content(schema = @Schema(implementation = ResponseGeneral.class))),
			@ApiResponse(responseCode = "400", description = "operacion KO")
	})
	public ResponseEntity<ResponseGeneral> obtenerHeroes(String id) {
		return ResponseEntity.ok(heroService.obtenerHeroe(id));
	}
	
	@PostMapping("/heroe")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",description = "operacion OK", content = @Content(schema = @Schema(implementation = ResponseGeneral.class))),
			@ApiResponse(responseCode = "400", description = "operacion KO")
	})
	public ResponseEntity<ResponseGeneral> anyadirHeroe(@RequestBody Hero heroe) {
		return ResponseEntity.ok(heroService.anyadirHeroe(heroe));
	}
	
	@RequestMapping("/heroespatron")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",description = "operacion OK", content = @Content(schema = @Schema(implementation = ResponseGeneral.class))),
			@ApiResponse(responseCode = "400", description = "operacion KO")
	})
	public ResponseEntity<ResponseGeneral> contienenPatron(String patron) {
		return ResponseEntity.ok(heroService.obtenerPorPatron(patron));
	}
	
	@DeleteMapping("heroe")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",description = "operacion OK", content = @Content(schema = @Schema(implementation = ResponseGeneral.class))),
			@ApiResponse(responseCode = "400", description = "operacion KO")
	})
	public ResponseEntity<ResponseGeneral> borrarHeroe(String id) {
		return ResponseEntity.ok(heroService.borrarHeroe(id));
	}
	
	@PutMapping("heroe")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",description = "operacion OK", content = @Content(schema = @Schema(implementation = ResponseGeneral.class))),
			@ApiResponse(responseCode = "400", description = "operacion KO")
	})
	public ResponseEntity<ResponseGeneral> modificarHeroe(@RequestBody Hero hero) {
		return ResponseEntity.ok(heroService.modificarHeroe(hero));
	}
}
