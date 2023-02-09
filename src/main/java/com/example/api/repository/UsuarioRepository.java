package com.example.api.repository;

import org.springframework.stereotype.Service;

import com.example.api.entity.Usuario;

@Service
public interface UsuarioRepository extends BaseRepository<Usuario> {
  public Usuario findOneEmail(String email);
  // public void save2 (Usuario usuario);
}

