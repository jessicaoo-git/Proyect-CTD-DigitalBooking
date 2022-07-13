package com.digitalbooking.backend.Dto;

import com.digitalbooking.backend.Models.Producto;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ImagenDTO {
    private Integer id;
    private String titulo;
    private String url;
    private Producto producto;


    public ImagenDTO(String titulo, String url) {
        this.titulo = titulo;
        this.url = url;
    }

    public ImagenDTO(String titulo, String url, Producto producto) {
        this.titulo = titulo;
        this.url = url;
        this.producto = producto;
    }
}
