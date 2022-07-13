package com.digitalbooking.backend.Dto;

import com.digitalbooking.backend.Models.Producto;
import lombok.Data;

@Data
public class FavoritosDTO {
    private Integer usuario_id;
    private Producto producto_id;
}
