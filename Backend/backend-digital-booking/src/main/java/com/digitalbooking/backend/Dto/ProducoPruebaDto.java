package com.digitalbooking.backend.Dto;

import com.digitalbooking.backend.Models.Caracteristica;
import com.digitalbooking.backend.Models.Categoria;
import com.digitalbooking.backend.Models.Ciudad;
import com.digitalbooking.backend.Models.Politica;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class ProducoPruebaDto {
    private Integer id;
    private String titulo;
    private String descripcion;
    private Ciudad ciudad;
    private Categoria categoria;
    private boolean disponible;
    private ImagenMuestraDto imagen;
    private Set<Caracteristica> caracteristicas;
    private Set<Politica> politicas;




}
