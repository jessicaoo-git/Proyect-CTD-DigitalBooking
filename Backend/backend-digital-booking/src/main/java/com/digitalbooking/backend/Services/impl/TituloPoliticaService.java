package com.digitalbooking.backend.Services.impl;

import com.digitalbooking.backend.Dto.PaginaDTO;
import com.digitalbooking.backend.Dto.TituloPoliticaDTO;
import com.digitalbooking.backend.Exceptions.EntityNotFoundException;
import com.digitalbooking.backend.Models.TituloPolitica;
import com.digitalbooking.backend.Repository.ITituloPoliticaRepository;
import com.digitalbooking.backend.Services.ITituloPolitica;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TituloPoliticaService implements ITituloPolitica {
    @Autowired
    private ITituloPoliticaRepository tituloPoliticaRepository;
    @Autowired
    private ObjectMapper mapper;

    public static final String ENTITY_NOT_FOUND_MESSAGE  = "No se encontro la tituloTituloPolitica con el id indicado";
    
    @Override
    public TituloPoliticaDTO findById(Integer id) {
        TituloPolitica tituloTituloPolitica = tituloPoliticaRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(ENTITY_NOT_FOUND_MESSAGE));
        return mapDTO(tituloTituloPolitica);
    }

    @Override
    public PaginaDTO<TituloPoliticaDTO> findAll(Integer page, Integer size) {
        page=page==null?0:page;
        size=size==null?8:size;
        Pageable pageRequest= PageRequest.of(page,size);
        Page<TituloPolitica> paginaTituloPoliticas=tituloPoliticaRepository.findAll(pageRequest);
        List<TituloPolitica> listaTituloPoliticas = paginaTituloPoliticas.getContent();
        List<TituloPoliticaDTO> listaTituloPoliticasDto=
                listaTituloPoliticas.stream().map(this::mapDTO).collect(Collectors.toList());
        long numeroTituloPoliticas = paginaTituloPoliticas.getTotalElements();
        return new PaginaDTO<>(page,size,numeroTituloPoliticas,listaTituloPoliticasDto);
    
    }
    //------ MAPPER----
    private TituloPoliticaDTO mapDTO(TituloPolitica tituloTituloPolitica){
        return mapper.convertValue(tituloTituloPolitica, TituloPoliticaDTO.class);
    }

    public TituloPolitica mapEntity(TituloPoliticaDTO tituloTituloPoliticaDTO){
        return mapper.convertValue(tituloTituloPoliticaDTO, TituloPolitica.class);
    }
}
