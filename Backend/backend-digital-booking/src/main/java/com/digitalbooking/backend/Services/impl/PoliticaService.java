package com.digitalbooking.backend.Services.impl;

import com.digitalbooking.backend.Dto.PaginaDTO;
import com.digitalbooking.backend.Dto.PoliticaDTO;
import com.digitalbooking.backend.Exceptions.EntityNotFoundException;
import com.digitalbooking.backend.Exceptions.InvalidIdException;
import com.digitalbooking.backend.Models.Politica;
import com.digitalbooking.backend.Repository.IPoliticaRepository;
import com.digitalbooking.backend.Services.IPoliticaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PoliticaService implements IPoliticaService {

    @Autowired
    private IPoliticaRepository politicaRepository;
    @Autowired
    private ObjectMapper mapper;

    public static final String ENTITY_NOT_FOUND_MESSAGE  = "No se encontro la politica con el id indicado";

    @Override
    public PoliticaDTO create(PoliticaDTO obj) {
        Politica politica = mapEntity(obj);
        Politica newpolitica = politicaRepository.save(politica);
        return mapDTO(newpolitica);
    }

    @Override
    public PoliticaDTO update(PoliticaDTO obj) {
        int id = obj.getId();
        if(id <= 0) throw new InvalidIdException();
        politicaRepository.findById(obj.getId())
                .orElseThrow(()-> new EntityNotFoundException(ENTITY_NOT_FOUND_MESSAGE));
        Politica politica = mapEntity(obj);
        Politica newpolitica = politicaRepository.save(politica);
        return mapDTO(newpolitica);
    }

    @Override
    public PoliticaDTO findById(Integer id) {
        Politica politica = politicaRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(ENTITY_NOT_FOUND_MESSAGE));
        return mapDTO(politica);
    }

    @Override
    public void deleteById(Integer id) {
        politicaRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(ENTITY_NOT_FOUND_MESSAGE));
        politicaRepository.deleteById(id);
    }

    @Override
    public PaginaDTO<PoliticaDTO> findAll(Integer page, Integer size) {
        page=page==null?0:page;
        size=size==null?8:size;
        Pageable pageRequest= PageRequest.of(page,size);
        Page<Politica> paginaPoliticas=politicaRepository.findAll(pageRequest);
        List<Politica> listaPoliticas = paginaPoliticas.getContent();
        List<PoliticaDTO> listaPoliticasDto=
                listaPoliticas.stream().map(this::mapDTO).collect(Collectors.toList());
        long numeroPoliticas = paginaPoliticas.getTotalElements();
        return new PaginaDTO<>(page,size,numeroPoliticas,listaPoliticasDto);
    }

    //------ MAPPER----
    private PoliticaDTO mapDTO(Politica politica){
        return mapper.convertValue(politica, PoliticaDTO.class);
    }

    public Politica mapEntity(PoliticaDTO politicaDTO){
        return mapper.convertValue(politicaDTO, Politica.class);
    }

}
