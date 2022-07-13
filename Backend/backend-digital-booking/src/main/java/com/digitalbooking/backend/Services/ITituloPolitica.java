package com.digitalbooking.backend.Services;

import com.digitalbooking.backend.Dto.PaginaDTO;
import com.digitalbooking.backend.Dto.ReservaDTO;
import com.digitalbooking.backend.Dto.TituloPoliticaDTO;

public interface ITituloPolitica {
    TituloPoliticaDTO findById(Integer id);
    PaginaDTO<TituloPoliticaDTO> findAll(Integer page, Integer size);
}
