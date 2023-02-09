package com.example.api.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.api.entity.Usuario;



@Repository
public class UsuarioRepositoryImpl extends BaseRepositoryImpl <Usuario> implements UsuarioRepository {
  
  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public Usuario findOneEmail(String correo) {    
      String SQL= "select * from usuario where cuenta='%s'";
      SQL=String.format(SQL,correo);

      System. out. println(SQL);
      return jdbcTemplate.queryForObject(SQL, BeanPropertyRowMapper.newInstance(Usuario.class));
  }
  // @Override
  // public void save2 (Usuario usuario){
  //   String SQL= "INSERT INTO USUARIO(nombres,apellidos,cuenta,contrasena,correo)VALUES('%s','%s','%s','%s','%s')";
  //   SQL=String.format(SQL,usuario.getNombres(),usuario.getApellidos(),usuario.getCuenta(),usuario.getContrasena(),usuario.getCorreo());
  //   jdbcTemplate.execute(SQL);
  // }
}
