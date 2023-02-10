package com.example.api.dto;

import javax.validation.constraints.NotBlank;

import com.example.api.config.Validation_Login;

import lombok.Data;

@Data
public class UsuarioDTO {
	
	int id;
	String nombres;	
	String apellidos;
	@NotBlank(message="campo en blanco cuenta",groups = Validation_Login.class)
	String cuenta;
	@NotBlank(message="campo en blanco password",groups = Validation_Login.class)
	String contrasena;
	String correo;

}
