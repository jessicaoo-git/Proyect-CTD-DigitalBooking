package com.digitalbooking.backend.Filtros;

import lombok.Data;

import java.time.Period;
import java.util.Date;

@Data
public class FiltroProductos {
    private Integer ciudadId;
    private Integer categoriaId;
    private Date fechaInicio;
    private Date fechaFin;

    public boolean hasCiudadId() {
        return ciudadId != null;
    }
    public boolean hasCategoriaId() {
        return categoriaId != null;
    }
    public boolean hasPeriodo() {
        return fechaInicio != null && fechaFin != null;
    }
}
