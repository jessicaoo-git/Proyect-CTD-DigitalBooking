package com.digitalbooking.backend.Dto;

import com.digitalbooking.backend.Models.Producto;
import com.digitalbooking.backend.Security.entity.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@NoArgsConstructor
@Data
public class ReservaDTOList {

    private Integer id;
    private Integer horaComienzo;
    private Date fechaInicial;
    private Date fechaFinal;

    public ReservaDTOList(Integer horaComienzo, Date fechaInicial, Date fechaFinal) {
        this.horaComienzo = horaComienzo;
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
    }
}
