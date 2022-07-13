package com.digitalbooking.backend.Services.impl;

import com.digitalbooking.backend.Dto.CaracteristicaDTO;
import com.digitalbooking.backend.Dto.PaginaDTO;
import com.digitalbooking.backend.Exceptions.EntityNotFoundException;
import com.digitalbooking.backend.Exceptions.InvalidIdException;
import com.digitalbooking.backend.Models.Caracteristica;
import com.digitalbooking.backend.Repository.ICaracteristicaRepository;
import com.digitalbooking.backend.Services.ICaracteristicaService;
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
public class CaracteristicaService implements ICaracteristicaService{

    @Autowired
    private ICaracteristicaRepository caracteristicaRepository;
    @Autowired
    private ObjectMapper mapper;

    public static final  String ENTITY_NOT_FOUND_MESSAGE  = "No se encontro la caracteristica con el id indicado";

    @Override
    public CaracteristicaDTO create(CaracteristicaDTO obj) {
        Caracteristica caracteristica = mapEntity(obj);
        Caracteristica newcaracteristica = caracteristicaRepository.save(caracteristica);
        return mapDTO(newcaracteristica);
    }

    @Override
    public CaracteristicaDTO update(CaracteristicaDTO obj) {
        int id = obj.getId();
        if(id <= 0) throw new InvalidIdException();
        caracteristicaRepository.findById(obj.getId())
                .orElseThrow(()-> new EntityNotFoundException(ENTITY_NOT_FOUND_MESSAGE));
        Caracteristica caracteristicasave = caracteristicaRepository.save(mapEntity(obj));
        return mapDTO(caracteristicasave);
    }

    @Override
    public CaracteristicaDTO findById(Integer id) {
       Caracteristica caracteristica = caracteristicaRepository.findById(id)
               .orElseThrow(()-> new EntityNotFoundException(ENTITY_NOT_FOUND_MESSAGE));
       return mapDTO(caracteristica);
    }

    @Override
    public void deleteById(Integer id) {
        caracteristicaRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(ENTITY_NOT_FOUND_MESSAGE));
        caracteristicaRepository.deleteById(id);
        System.out.println("Caracteristica Eliminada");
    }

    @Override
    public PaginaDTO<CaracteristicaDTO> findAll(Integer page, Integer size) {
        page=page==null?0:page;
        size=size==null?8:size;
        Pageable pageRequest= PageRequest.of(page,size);
        Page<Caracteristica> paginaCaracteristicas=caracteristicaRepository.findAll(pageRequest);
        List<Caracteristica> listaCaracteristicas = paginaCaracteristicas.getContent();
        List<CaracteristicaDTO> listaCaracteristicasDto=
                listaCaracteristicas.stream().map(this::mapDTO).collect(Collectors.toList());
        long numeroCaracteristicas = paginaCaracteristicas.getTotalElements();
        return new PaginaDTO<>(page,size,numeroCaracteristicas,listaCaracteristicasDto);
    }

    private CaracteristicaDTO mapDTO(Caracteristica caracteristica){
        return mapper.convertValue(caracteristica, CaracteristicaDTO.class);
    }

    public Caracteristica mapEntity(CaracteristicaDTO caracteristicaDTO){
        return mapper.convertValue(caracteristicaDTO, Caracteristica.class);
    }

}

