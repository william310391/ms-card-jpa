package com.example.api.service;

import org.springframework.http.ResponseEntity;

import com.example.api.dto.UsuarioDTO;


public interface AuthenticationService {
 
  public ResponseEntity<?> loginUser(UsuarioDTO usuario);
  public ResponseEntity<?> saveUser(UsuarioDTO usuarioDTO);
  
}
