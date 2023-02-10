package com.example.api.service;


import java.util.HashMap;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.example.api.dto.AuthenticationDTO;
import com.example.api.dto.ResponseDTO;
import com.example.api.dto.UsuarioDTO;
import com.example.api.entity.Usuario;
import com.example.api.repository.UsuarioRepository;
import com.example.api.util.JwtTokenUtil;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
  @Autowired
  private UsuarioRepository iUsuarioRepository;
  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private JwtUserDetailsService userDetailsService;
  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  protected final Log logger = LogFactory.getLog(getClass());



  @Override
  public ResponseEntity<?> loginUser(UsuarioDTO usuario) {

    ResponseDTO<AuthenticationDTO> response = new ResponseDTO<AuthenticationDTO>(null);
    response.setResultadoIndicador(0);
    
    try {
      Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usuario.getCuenta(), usuario.getContrasena()));
      
      if (auth.isAuthenticated()) {
        logger.info("Logged In");
        UserDetails userDetails = userDetailsService.loadUserByUsername(usuario.getCuenta());
        String token = jwtTokenUtil.generateToken(userDetails);    
        AuthenticationDTO dto = new AuthenticationDTO();


        dto.setNombreUsuario("Prueba");
        dto.setUsuario("sss");
        dto.setToken(token);     
        response.setData(dto);
        response.setCodigoHTTP(200);
        response.setResultadoDescripcion("Logged In");
        response.setResultadoIndicador(1); 
  
      } else {

        response.setResultadoDescripcion("Invalid Credentials");
        response.setCodigoHTTP(401);       

      }
    } catch (DisabledException e) {

      response.setResultadoDescripcion("User is disabled");
      response.setCodigoHTTP(500);
      e.printStackTrace();
      
      //return ResponseEntity.status(500).body(responseMap);

    } catch (BadCredentialsException e) {

      response.setResultadoDescripcion("Invalid Credentials");
      response.setCodigoHTTP(401);
      e.printStackTrace();

    } 
    catch (Exception e) {
      response.setResultadoDescripcion("Something went wrong");
      response.setCodigoHTTP(500);
      e.printStackTrace();
    }

    return ResponseEntity.status(response.getCodigoHTTP()).body(response); 
  }

  @Override
  public ResponseEntity<?> saveUser(UsuarioDTO usuarioDTO) {
    Map<String, Object> responseMap = new HashMap<>();

    Usuario user = new Usuario();
    user.setNombres(usuarioDTO.getNombres());
    user.setApellidos(usuarioDTO.getApellidos());
    user.setCorreo(usuarioDTO.getCorreo());
    user.setContrasena(new BCryptPasswordEncoder().encode(usuarioDTO.getContrasena()));
    // user.setRole("USER");
    user.setCuenta(usuarioDTO.getCuenta());

    UserDetails userDetails = userDetailsService.createUserDetails(usuarioDTO.getCuenta(), user.getContrasena());
    String token = jwtTokenUtil.generateToken(userDetails);
    iUsuarioRepository.save(user);
    responseMap.put("error", false);
    responseMap.put("username", usuarioDTO.getCuenta());
    responseMap.put("message", "Account created successfully");
    responseMap.put("token", token);
    return ResponseEntity.ok(responseMap);
  }

}
