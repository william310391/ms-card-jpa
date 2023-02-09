package com.example.api.dto;

import lombok.Data;

@Data
public class UsuarioDTO {
  int id;
	String nombres;
	String apellidos; 
	String cuenta;
	String contrasena;
	String correo;
  
}
