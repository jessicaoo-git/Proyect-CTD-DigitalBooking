package com.digitalbooking.backend.Dto;

import com.digitalbooking.backend.Models.Producto;
import com.digitalbooking.backend.Security.entity.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@NoArgsConstructor
@Data
public class ReservaDTO {

        private Integer id;
        private Integer horaComienzo;
        private Date fechaInicial;
        private Date fechaFinal;
        private Producto producto;
        private Usuario usuario;

    public ReservaDTO(Integer horaComienzo, Date fechaInicial, Date fechaFinal, Producto producto, Usuario usuario) {
        this.horaComienzo = horaComienzo;
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
        this.producto = producto;
        this.usuario = usuario;
    }
}
