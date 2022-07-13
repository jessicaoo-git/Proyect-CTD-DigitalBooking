package com.digitalbooking.backend.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
@Data
public class ImagenMuestraDto {
    private Integer id;
    private String titulo;
    private String url;

    public ImagenMuestraDto(Integer id, String titulo, String url) {
        this.id = id;
        this.titulo = titulo;
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImagenMuestraDto that = (ImagenMuestraDto) o;
        return id.equals(that.id) && titulo.equals(that.titulo) && url.equals(that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, url);
    }
}
