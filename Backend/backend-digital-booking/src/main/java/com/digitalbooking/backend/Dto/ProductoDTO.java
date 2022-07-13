package com.digitalbooking.backend.Dto;

import com.digitalbooking.backend.Models.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Data
public class ProductoDTO {
    private Integer id;
    private String titulo;
    private String descripcion;
    private Ciudad ciudad;
    private Categoria categoria;
    private boolean disponible;
    private Set<Imagen> imagenes;
    private Set<Caracteristica> caracteristicas;
    private Set<Politica> politicas;


    public ProductoDTO( String titulo, String descripcion, Ciudad ciudad, Categoria categoria, boolean disponible) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.ciudad = ciudad;
        this.categoria = categoria;
        this.disponible = disponible;
        this.imagenes = new HashSet<>();
        this.politicas = new HashSet<>();
        this.caracteristicas= new HashSet<>();
    }
}
