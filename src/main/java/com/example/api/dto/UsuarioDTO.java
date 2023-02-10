package com.example.api.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.example.api.exception.ValidationGroup.Login;

import lombok.Data;

@Data
public class UsuarioDTO {
	
	int id;
	String nombres;	
	String apellidos;
	
	@NotBlank(message = "campo requerido cuenta NotBlank",groups = Login.class)
	@NotNull(message = "campo requerido cuenta NotNull",groups = Login.class)
	String cuenta;

	@NotNull(message = "campo requerido passssss",groups = Login.class)
	@NotEmpty(message = "campo requerido passssss",groups = Login.class)
	String contrasena;
	String correo;

}
