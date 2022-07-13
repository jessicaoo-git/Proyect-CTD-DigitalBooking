package com.digitalbooking.backend.Dto;

import com.digitalbooking.backend.Models.Producto;
import com.digitalbooking.backend.Models.TituloPolitica;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@Data
public class PoliticaDTO {
    private Integer id;
    private Set<Producto> productos;
    private TituloPolitica titulo;
    private String descripcion;


    public PoliticaDTO(Integer id, TituloPolitica titulo, String descripcion) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
    }
}
