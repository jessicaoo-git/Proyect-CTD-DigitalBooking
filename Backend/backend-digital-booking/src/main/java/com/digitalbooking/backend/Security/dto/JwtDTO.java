package com.digitalbooking.backend.Security.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter@Setter
public class JwtDTO {

    private Integer id;
    private String token;
    private String bearer = "Bearer";
    private String nombreUsuario;
    private String nombre;
    private String apellido;
    private String email;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtDTO(String token, String nombreUsuario, Collection<? extends GrantedAuthority> authorities) {
        this.token = token;
        this.nombreUsuario = nombreUsuario;
        this.authorities = authorities;
    }

    public JwtDTO(String token, String nombreUsuario, String nombre, String apellido, String email, Collection<? extends GrantedAuthority> authorities) {
        this.token = token;
        this.nombreUsuario = nombreUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.authorities = authorities;
    }
    public JwtDTO(String token, Integer id, String nombreUsuario, String nombre, String apellido, String email, Collection<? extends GrantedAuthority> authorities) {
        this.token = token;
        this.nombreUsuario = nombreUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.authorities = authorities;
        this.id=id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
