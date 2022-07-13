package com.digitalbooking.backend.Services.impl;

import com.digitalbooking.backend.Dto.ImagenDTO;
import com.digitalbooking.backend.Dto.PaginaDTO;
import com.digitalbooking.backend.Exceptions.EntityNotFoundException;
import com.digitalbooking.backend.Exceptions.InvalidIdException;
import com.digitalbooking.backend.Models.Imagen;
import com.digitalbooking.backend.Repository.IImagenRepository;
import com.digitalbooking.backend.Services.IImagenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImagenService implements IImagenService {
    @Autowired
    private IImagenRepository imagenRepository;
    @Autowired
    private ObjectMapper mapper;

    public static final String ENTITY_NOT_FOUND_MESSAGE  = "No se encontro la imagen con el id indicado";

    @Override
    public ImagenDTO create(ImagenDTO obj) {
        Imagen imagen = mapEntity(obj);
        Imagen newimagen = imagenRepository.save(imagen);
        return mapDTO(newimagen);
    }

    @Override
    public ImagenDTO update(ImagenDTO obj) {
        int id = obj.getId();
        if(id <= 0) throw new InvalidIdException();
        imagenRepository.findById(obj.getId())
                .orElseThrow(()-> new EntityNotFoundException(ENTITY_NOT_FOUND_MESSAGE));
        Imagen imagen = mapEntity(obj);
         Imagen imagenupdate = imagenRepository.save(imagen);
        return mapDTO(imagenupdate);
    }

    @Override
    public ImagenDTO findById(Integer id) {
        if(id==null || id <= 0) throw new InvalidIdException();
        Imagen imagen = imagenRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(ENTITY_NOT_FOUND_MESSAGE));
        return mapDTO(imagen);
    }

    @Override
    public void deleteById(Integer id) {
        if(id==null || id <= 0) throw new InvalidIdException();
        imagenRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException(ENTITY_NOT_FOUND_MESSAGE));
        imagenRepository.deleteById(id);
    }

    @Override
    public PaginaDTO<ImagenDTO> findAll(Integer page, Integer size) {
        page=page==null?0:page;
        size=size==null?8:size;
        Pageable pageRequest= PageRequest.of(page,size);
        Page<Imagen> paginaImagenes=imagenRepository.findAll(pageRequest);
        List<Imagen> listaImagenes = paginaImagenes.getContent();
        List<ImagenDTO> listaImagenesDto=
                listaImagenes.stream().map(this::mapDTO).collect(Collectors.toList());
        long numeroImagenes = paginaImagenes.getTotalElements();
        return new PaginaDTO<>(page,size,numeroImagenes,listaImagenesDto);
    }

    //------ MAPPER----
    public Imagen mapEntity(ImagenDTO imagenDTO){
        return mapper.convertValue(imagenDTO, Imagen.class);
    }
    public ImagenDTO mapDTO(Imagen imagen){
        return mapper.convertValue(imagen, ImagenDTO.class);
    }
}
