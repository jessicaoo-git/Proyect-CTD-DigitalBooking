package com.digitalbooking.backend.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CiudadDTO {
    private Integer id;
    private String nombre;

    public CiudadDTO(String nombre) {
        this.nombre = nombre;
    }
}
