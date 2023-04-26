package com.app.controller;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@FieldDefaults(level= AccessLevel.PRIVATE)
public class ResponseGeneral {

	@Schema(description = "resultado")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	String resultado;
	
	@Schema(description = "error")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	String error;
	
	@Schema(description = "objeto")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	Object objeto;

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public Object getObjeto() {
		return objeto;
	}

	public void setObjeto(Object objeto) {
		this.objeto = objeto;
	}

}
