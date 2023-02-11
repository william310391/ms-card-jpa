package com.example.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.api.dto.ResponseDTO;
import com.example.api.dto.UsuarioDTO;
import com.example.api.entity.Usuario;
import com.example.api.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepository iUsuarioRepository;

    @Override
    public ResponseEntity<?> ListaUsuario() {

        List<UsuarioDTO> listaDTO = new ArrayList<UsuarioDTO>();

        List<Usuario> lista = iUsuarioRepository.findAll();  
        
        lista.forEach((ent)->{
            UsuarioDTO e = new UsuarioDTO();
            e.setId(ent.getId());
            e.setNombres(ent.getNombres());
            e.setApellidos(ent.getApellidos());
            e.setCuenta(ent.getCuenta());
            e.setCorreo(ent.getCorreo());
            listaDTO.add(e);
        });


        ResponseDTO<List<UsuarioDTO>> response = new ResponseDTO<List<UsuarioDTO>>(listaDTO);        

        return ResponseEntity.status(response.getCodigoHTTP()).body(response); 
    }
}
