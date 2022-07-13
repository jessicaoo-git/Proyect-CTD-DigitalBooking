package com.digitalbooking.backend.Repository.custom;

import com.digitalbooking.backend.Filtros.FiltroProductos;
import com.digitalbooking.backend.Models.Producto;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface CustomProductoRepository {
    CustomPage<Producto> findByFilters(FiltroProductos filtros,Pageable pageable);
}
