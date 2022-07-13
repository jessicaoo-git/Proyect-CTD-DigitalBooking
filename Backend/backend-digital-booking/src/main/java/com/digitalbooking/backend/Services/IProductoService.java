package com.digitalbooking.backend.Services;

import com.digitalbooking.backend.Dto.PaginaDTO;
import com.digitalbooking.backend.Dto.ProductoDTO;
import com.digitalbooking.backend.Filtros.FiltroProductos;
import com.digitalbooking.backend.Models.Producto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductoService extends ICRUDService<ProductoDTO>{
    PaginaDTO findByCiudadId(Integer id, Integer page, Integer size);
    PaginaDTO findByCategoriaId(Integer id, Integer page, Integer size);
    PaginaDTO findByFilters(FiltroProductos filtros, Integer page, Integer size);
}
