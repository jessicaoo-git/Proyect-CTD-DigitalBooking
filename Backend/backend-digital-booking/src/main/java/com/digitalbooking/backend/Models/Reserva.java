package com.digitalbooking.backend.Models;

import com.digitalbooking.backend.Security.entity.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "reservas")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private Integer horaComienzo;
    @Column(nullable = false)
    private Date fechaInicial;
    @Column(nullable = false)
    private Date fechaFinal;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="producto_id",nullable = false)
    private Producto producto;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="usuario_id",nullable = false)
    private Usuario usuario;

}
