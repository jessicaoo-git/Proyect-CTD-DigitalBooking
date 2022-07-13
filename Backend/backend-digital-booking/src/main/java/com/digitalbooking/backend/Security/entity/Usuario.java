package com.digitalbooking.backend.Security.entity;

import com.digitalbooking.backend.Models.Producto;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter@Setter
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String apellido;
    @Column(unique = true, nullable = false)
    private String nombreUsuario;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @ManyToMany
    @JoinTable(name = "usuarios_x_roles",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<Rol> roles=new HashSet<>();
    @Column(name = "verification_code", length = 64)
    private String verificationCode;
    @Column(name = "enabled", nullable = false)
    private boolean enabled;
    @ManyToMany
    @JoinTable(name = "favoritos",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "producto_id"))
    private Set<Producto> productos=new HashSet<>();

    public Usuario() {
    }
    public Usuario(Integer id) {
        this.id=id;
    }

    public Usuario(String nombre, String apellido, String nombreUsuario, String email, String password, String verificationCode, boolean enabled) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.password = password;
        this.verificationCode = verificationCode;
        this.enabled = enabled;
    }

    public Usuario(String nombre,
                   String apellido,
                   String nombreUsuario,
                   String email,
                   String password) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.password = password;
    }

}
