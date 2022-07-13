package com.digitalbooking.backend.Services.impl;

import com.digitalbooking.backend.Dto.CiudadDTO;
import com.digitalbooking.backend.Dto.PaginaDTO;
import com.digitalbooking.backend.Exceptions.EntityNotFoundException;
import com.digitalbooking.backend.Exceptions.InvalidIdException;
import com.digitalbooking.backend.Models.Ciudad;
import com.digitalbooking.backend.Repository.ICiudadRepository;
import com.digitalbooking.backend.Services.ICiudadService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CiudadService implements ICiudadService {
    @Autowired
    private ICiudadRepository ciudadRepository;
    @Autowired
    private ObjectMapper mapper;

    public static final String ENTITY_NOT_FOUND_MESSAGE  = "No se encontro la ciudad con el id indicado";

    @Override
    public CiudadDTO create(CiudadDTO obj) {
        Ciudad ciudad = mapEntity(obj);
        Ciudad newciudad = ciudadRepository.save(ciudad);
        return mapDTO(newciudad);
    }

    @Override
    public CiudadDTO update(CiudadDTO obj) {
        int id = obj.getId();
        if(id <= 0) throw new InvalidIdException();
        ciudadRepository.findById(obj.getId())
                .orElseThrow(()-> new EntityNotFoundException(ENTITY_NOT_FOUND_MESSAGE));
        Ciudad ciudadsave = ciudadRepository.save(mapEntity(obj));
        return mapDTO(ciudadsave);
    }

    @Override
    public CiudadDTO findById(Integer id) {
        Ciudad ciudad = ciudadRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(ENTITY_NOT_FOUND_MESSAGE));
        return mapDTO(ciudad);
    }

    @Override
    public void deleteById(Integer id) {
        ciudadRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(ENTITY_NOT_FOUND_MESSAGE));
        ciudadRepository.deleteById(id);
    }

    @Override
    public PaginaDTO<CiudadDTO> findAll(Integer page, Integer size) {
        page=page==null?0:page;
        size=size==null?8:size;
        Pageable pageRequest= PageRequest.of(page,size);
        Page<Ciudad> paginaCiudades=ciudadRepository.findAll(pageRequest);
        List<Ciudad> listaCiudades = paginaCiudades.getContent();
        List<CiudadDTO> listaCiudadesDto=
                listaCiudades.stream().map(this::mapDTO).collect(Collectors.toList());
        long numeroCiudades = paginaCiudades.getTotalElements();
        return new PaginaDTO<>(page,size,numeroCiudades,listaCiudadesDto);
    }
    @Override
    public List<CiudadDTO> guardarTodos(List<CiudadDTO> listaCiudad){
        List<Ciudad> listaCiudadEntity = listaCiudad.stream().map(this::mapEntity).collect(Collectors.toList());
        List<Ciudad> listaCiudadSave = ciudadRepository.saveAll(listaCiudadEntity);
        return listaCiudadSave.stream().map(this::mapDTO).collect(Collectors.toList());
    }

    @Override
    public List<CiudadDTO> findByNombre(String fragment) {
        List<Ciudad> listaCiudad = ciudadRepository.findByNombre(fragment);
        return listaCiudad.stream().map(this::mapDTO).collect(Collectors.toList());
    }

    //Mapers
    private CiudadDTO mapDTO(Ciudad ciudad){
        return mapper.convertValue(ciudad, CiudadDTO.class);
    }

    public Ciudad mapEntity(CiudadDTO ciudadDTO){
        return mapper.convertValue(ciudadDTO, Ciudad.class);
    }
}
