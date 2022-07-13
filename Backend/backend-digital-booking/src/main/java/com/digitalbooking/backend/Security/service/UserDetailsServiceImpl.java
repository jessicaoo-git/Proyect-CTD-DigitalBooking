package com.digitalbooking.backend.Security.service;

import com.digitalbooking.backend.Security.entity.Usuario;
import com.digitalbooking.backend.Security.entity.UsuarioMain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Usuario usuario = usuarioService.findByNombreUsuario(username).get();
       return UsuarioMain.build(usuario);
    }
}
