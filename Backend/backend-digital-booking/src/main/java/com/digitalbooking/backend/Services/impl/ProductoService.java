package com.digitalbooking.backend.Services.impl;


import com.digitalbooking.backend.Dto.PaginaDTO;
import com.digitalbooking.backend.Dto.ProductoDTO;
import com.digitalbooking.backend.Exceptions.EntityNotFoundException;
import com.digitalbooking.backend.Exceptions.InvalidIdException;
import com.digitalbooking.backend.Filtros.FiltroProductos;
import com.digitalbooking.backend.Models.Producto;
import com.digitalbooking.backend.Repository.IProductoRepository;
import com.digitalbooking.backend.Repository.custom.CustomPage;
import com.digitalbooking.backend.Services.IProductoService;
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
public class ProductoService implements IProductoService {

    @Autowired(required = false)
    private IProductoRepository productoRepository;
    @Autowired
    private ObjectMapper mapper;

    public static final String ENTITY_NOT_FOUND_MESSAGE  = "No se encontro el producto con el id indicado";

    @Override
    public ProductoDTO create(ProductoDTO obj) {
        Producto producto = mapEntity(obj);
        Producto newproducto = productoRepository.save(producto);
        return mapDTO(newproducto);
    }

    @Override
    public ProductoDTO update(ProductoDTO obj) {
        int id = obj.getId();
        if(id <= 0) throw new InvalidIdException();
        productoRepository.findById(obj.getId())
                .orElseThrow(()-> new EntityNotFoundException(ENTITY_NOT_FOUND_MESSAGE));
        Producto productosave = productoRepository.save(mapEntity(obj));
        return mapDTO(productosave);
    }

    @Override
    public ProductoDTO findById(Integer id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(ENTITY_NOT_FOUND_MESSAGE));
        return mapDTO(producto);
    }

    @Override
    public void deleteById(Integer id) {
        productoRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(ENTITY_NOT_FOUND_MESSAGE));
        productoRepository.deleteById(id);
    }

    @Override
    public PaginaDTO<ProductoDTO> findAll(Integer page, Integer size) {
        page=page==null?0:page;
        size=size==null?8:size;
        Pageable pageRequest= PageRequest.of(page,size);
        Page<Producto> pagina=productoRepository.findAllWithOneImage(pageRequest);
        List<Producto> contenido = pagina.getContent();
        List<ProductoDTO> listaProductosDto=
        contenido.stream().map(this::mapDTO).collect(Collectors.toList());
        long numeroProductos = pagina.getTotalElements();
        return new PaginaDTO<>(page,size,numeroProductos,listaProductosDto);
    }

    @Override
    public PaginaDTO<ProductoDTO> findByCiudadId(Integer ciudadId,Integer page, Integer size) {
        page=page==null?0:page;
        size=size==null?8:size;
        Pageable pageRequest= PageRequest.of(page,size);
        Page<Producto> paginaProductos = productoRepository.findByCiudad_Id(ciudadId,pageRequest);
        List<Producto> listaProductos = paginaProductos.getContent();
        long numeroProductos = paginaProductos.getTotalElements();
        List<ProductoDTO> listaProductosDto=
                listaProductos.stream().map(this::mapDTO).collect(Collectors.toList());
        return new PaginaDTO<>(page,size,numeroProductos,listaProductosDto);
    }


    @Override
    public PaginaDTO<ProductoDTO> findByCategoriaId(Integer categoriaId,Integer page, Integer size) {
        page=page==null?0:page;
        size=size==null?8:size;
        Pageable pageRequest= PageRequest.of(page,size);
        Page<Producto> paginaProductos = productoRepository.findByCategoria_Id(categoriaId,pageRequest);
        List<Producto> listaProductos = paginaProductos.getContent();
        long numeroProductos = paginaProductos.getTotalElements();
        List<ProductoDTO> listaProductosDto=
                listaProductos.stream().map(this::mapDTO).collect(Collectors.toList());
        return new PaginaDTO<>(page,size,numeroProductos,listaProductosDto);
    }

    @Override
    public PaginaDTO findByFilters(FiltroProductos filtros, Integer page, Integer size) {
        page=page==null?0:page;
        size=size==null?8:size;
        Pageable pageRequest= PageRequest.of(page,size);
        CustomPage<Producto> customPage = productoRepository.findByFilters(filtros,pageRequest);
        List<Producto> listaProductos =  customPage.getContent();
        List<ProductoDTO> listaProductosDto=
                listaProductos.stream().map(this::mapDTO).collect(Collectors.toList());
        long numeroProductos = customPage.getTotalElements();
        return new PaginaDTO<>(page,size,numeroProductos,listaProductosDto);
    }

    //------ MAPPER----
    private ProductoDTO mapDTO(Producto producto){
        return mapper.convertValue(producto, ProductoDTO.class);
    }

    public Producto mapEntity(ProductoDTO productoDTO){
        return mapper.convertValue(productoDTO, Producto.class);
    }
}
