package com.digitalbooking.backend.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CategoriaDTO {
    private Integer id;
    private String titulo;
    private String descripcion;
    private String urlImagen;

    public CategoriaDTO(String titulo, String descripcion, String urlImagen) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.urlImagen = urlImagen;
    }

}
