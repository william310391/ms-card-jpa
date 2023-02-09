package com.example.api.service;

import com.example.api.entity.Usuario;
import com.example.api.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {


    @Autowired
    private UsuarioRepository iUsuarioRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {      
        Usuario user =iUsuarioRepository.findOneEmail(username);
        List<GrantedAuthority> authorityList = new ArrayList<>();
        // authorityList.add(new SimpleGrantedAuthority("USER_ROLE"));
        return new User(user.getCuenta(), user.getContrasena(), authorityList);
    }

    public UserDetails createUserDetails(String username, String password) {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        // authorityList.add(new SimpleGrantedAuthority("USER_ROLE"));
        return new User(username, password, authorityList);
    }
}
