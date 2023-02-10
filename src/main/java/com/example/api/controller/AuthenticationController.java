package com.example.api.controller;

import com.example.api.config.Validation_Login;
import com.example.api.dto.UsuarioDTO;
import com.example.api.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;


    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Validated({Validation_Login.class}) @RequestBody UsuarioDTO usuario) {
        return authenticationService.loginUser(usuario);
    }

    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody UsuarioDTO usuarioDTO) {

        return authenticationService.saveUser(usuarioDTO);      
    }
}
