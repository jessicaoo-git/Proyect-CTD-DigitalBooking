package com.digitalbooking.backend.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="productos")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String titulo;
    @Column(nullable = false, columnDefinition="Text")
    private String descripcion;
    @Column(nullable = false)
    private boolean disponible;
    @ManyToOne
    @JoinColumn( name="categoria_id", nullable = false)
    private Categoria categoria;
    @ManyToOne
    @JoinColumn(name="ciudad_id",nullable = false)
    private Ciudad ciudad;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "producto")
    @JsonManagedReference
    private Set<Imagen> imagenes;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "productos_x_caracteristicas",
        joinColumns = @JoinColumn(name = "id_producto"),
        inverseJoinColumns = @JoinColumn(name = "id_caracteristica")
    )
    private Set<Caracteristica> caracteristicas;
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(
            name="productos_x_politicas",
            joinColumns = @JoinColumn(name="id_producto"),
            inverseJoinColumns = @JoinColumn(name="politica_id")
    )
    private Set<Politica> politicas;

    @OneToMany(mappedBy = "producto")
    @JsonIgnore
    private Set<Reserva> reservas;

}
