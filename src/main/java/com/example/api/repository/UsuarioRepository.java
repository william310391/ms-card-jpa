package com.example.api.repository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.api.entity.Usuario;

@Service
@Transactional
public interface UsuarioRepository extends BaseRepository<Usuario> {
  public Usuario findOneEmail(String email);
  // public void save2 (Usuario usuario);  



  
}

