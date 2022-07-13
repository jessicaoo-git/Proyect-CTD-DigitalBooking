package com.digitalbooking.backend.Services.impl;

import com.digitalbooking.backend.Dto.CategoriaDTO;
import com.digitalbooking.backend.Dto.PaginaDTO;
import com.digitalbooking.backend.Exceptions.EntityNotFoundException;
import com.digitalbooking.backend.Exceptions.InvalidIdException;
import com.digitalbooking.backend.Models.Categoria;
import com.digitalbooking.backend.Repository.ICategoriaRepository;
import com.digitalbooking.backend.Services.ICategoriaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService implements ICategoriaService {

    @Autowired(required = false)
    private ICategoriaRepository categoriaRepository;
    @Autowired
    private ObjectMapper mapper;

    public static final String ENTITY_NOT_FOUND_MESSAGE  = "No se encontro la categoria con el id indicado";

    @Override
    public CategoriaDTO findById(Integer id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(ENTITY_NOT_FOUND_MESSAGE));
        return mapDTO(categoria);
    }

    @Override
    public CategoriaDTO create(CategoriaDTO categoriadto) {
        Categoria categoria = mapEntity(categoriadto);
        Categoria newcategoria = categoriaRepository.save(categoria);
        return mapDTO(newcategoria);
    }

    @Override
    public void deleteById(Integer id) {
        categoriaRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(ENTITY_NOT_FOUND_MESSAGE));
        categoriaRepository.deleteById(id);
        System.out.println("Categoria Eliminada");
    }

    @Override
    public CategoriaDTO update(CategoriaDTO categoriaDTO) {
        int id = categoriaDTO.getId();
        if(id <= 0) throw new InvalidIdException();
        categoriaRepository.findById(categoriaDTO.getId())
                .orElseThrow(()-> new EntityNotFoundException(ENTITY_NOT_FOUND_MESSAGE));
        Categoria categoriasave = categoriaRepository.save(mapEntity(categoriaDTO));
        return mapDTO(categoriasave);
    }

    @Override
    public PaginaDTO<CategoriaDTO> findAll(Integer page, Integer size) {
        page=page==null?0:page;
        size=size==null?8:size;
        Pageable pageRequest= PageRequest.of(page,size);
        Page<Categoria> paginaCategorias=categoriaRepository.findAll(pageRequest);
        List<Categoria> listaCategorias = paginaCategorias.getContent();
        List<CategoriaDTO> listaCategoriasDto=
                listaCategorias.stream().map(this::mapDTO).collect(Collectors.toList());
        long numeroCategorias = paginaCategorias.getTotalElements();
        return new PaginaDTO<>(page,size,numeroCategorias,listaCategoriasDto);
    }


    //------ MAPPER----
    private CategoriaDTO mapDTO(Categoria categoria){
        return mapper.convertValue(categoria, CategoriaDTO.class);
    }

    public Categoria mapEntity(CategoriaDTO categoriaDTO){
        return mapper.convertValue(categoriaDTO, Categoria.class);
    }
}