package com.digitalbooking.backend.Security.entity;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class UsuarioMain implements UserDetails {
    private Integer id;
    private String nombre;
    private String apellido;
    private String username;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    private String verificationCode;
    private boolean enabled;


    public UsuarioMain(String nombre, String apellido, String username, String email, String password, Collection<? extends GrantedAuthority> authorities, String verificationCode, boolean enabled) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
        this.verificationCode = verificationCode;
        this.enabled = enabled;
    }

    public UsuarioMain(Integer id,String nombre, String apellido, String username, String email, String password, Collection<? extends GrantedAuthority> authorities, String verificationCode, boolean enabled) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
        this.verificationCode = verificationCode;
        this.enabled = enabled;
    }

    public static UsuarioMain build(Usuario usuario){
        List<GrantedAuthority> authorities = usuario.getRoles().stream()
                .map(
                        role -> new SimpleGrantedAuthority(role.getRolNombre().name())
                )
                .collect(Collectors.toList());
        return new UsuarioMain( usuario.getId(),usuario.getNombre(), usuario.getApellido(),usuario.getNombreUsuario(),
                usuario.getEmail(),usuario.getPassword(),authorities,usuario.getVerificationCode(),usuario.isEnabled());
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
