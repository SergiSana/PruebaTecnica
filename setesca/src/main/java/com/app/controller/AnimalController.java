package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.service.AnimalService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(path = "an")
public class AnimalController {

	@Autowired AnimalService animalService;
	
	@RequestMapping("/animales")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",description = "operacion OK", content = @Content(schema = @Schema(implementation = ResponseGeneral.class))),
			@ApiResponse(responseCode = "400", description = "operacion KO")
	})
	@ResponseBody
	public ResponseEntity<ResponseGeneral> obtenerAnimales() {
		
		return ResponseEntity.ok(animalService.listaAnimales());
	}
}
