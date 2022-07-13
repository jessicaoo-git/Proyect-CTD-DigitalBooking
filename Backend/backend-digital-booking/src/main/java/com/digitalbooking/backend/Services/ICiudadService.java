package com.digitalbooking.backend.Services;

import com.digitalbooking.backend.Dto.CiudadDTO;

import java.util.List;

public interface ICiudadService extends ICRUDService<CiudadDTO>{

    List<CiudadDTO> guardarTodos(List<CiudadDTO> listaCiudad);

    List<CiudadDTO>findByNombre(String fragment);
}
