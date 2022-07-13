package com.digitalbooking.backend.Services.impl;

import com.digitalbooking.backend.Dto.*;
import com.digitalbooking.backend.Models.Categoria;
import com.digitalbooking.backend.Models.Ciudad;
import com.digitalbooking.backend.Services.ICategoriaService;
import com.digitalbooking.backend.Services.ICiudadService;
import com.digitalbooking.backend.Services.IProductoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProductoServiceTest {

    @Autowired
    public IProductoService productoService;
    @Autowired
    public ICategoriaService categoriaService;
    @Autowired
    public ICiudadService ciudadService;
    @Autowired
    private ObjectMapper mapper;

    private Ciudad ciudad;
    private Categoria categoria;

    @BeforeAll
    void setUp() {
        CiudadDTO ciudadDTO = new CiudadDTO("ciudad0");
        ciudad = mapEntity(ciudadService.create(ciudadDTO));
        assertNotNull(ciudad);
        assertTrue(ciudad.getId() > 0);
        CategoriaDTO categoriaDTO = new CategoriaDTO("Cat1","cat1desc","url1");
        categoria = mapEntity(categoriaService.create(categoriaDTO));
        assertNotNull(categoria);
        assertTrue(categoria.getId() > 0);
    }

    @Test
    void findById() {
        ProductoDTO productoDTO= new ProductoDTO("producto0","pro0"
                ,ciudad,categoria,true);
        productoDTO=productoService.create(productoDTO);
        assertNotNull(productoDTO);
        ProductoDTO dt=productoService.findById(productoDTO.getId());
        assertNotNull(dt);
    }

    @Test
    void create() {
        ProductoDTO productoDTO= new ProductoDTO("producto1","pro1",ciudad
                ,categoria,true);
        productoDTO=productoService.create(productoDTO);
        assertNotNull(productoDTO);
        assertTrue(productoDTO.getId()>0);
    }

    @Test
    void deleteById() {
        ProductoDTO productoDTO= new ProductoDTO("producto3","pro3",ciudad
                ,categoria,true);
        productoDTO=productoService.create(productoDTO);
        int id= productoDTO.getId();
        productoService.deleteById(id);
        ProductoDTO dt=null;
        try {
            dt = productoService.findById(id);
        }
        catch (Exception e){
            assertTrue(e.getMessage().contains("No se encontro el producto"));
        }
        assertNull(dt);
    }

    @Test
    void update() {

        String titulo="producto4";
        String descripcion="prod4";
        boolean disponible=true;

        ProductoDTO productoDTO= new ProductoDTO(titulo,descripcion,ciudad,categoria,disponible);
        productoDTO=productoService.create(productoDTO);

        String nuevoTitulo="producto777";
        productoDTO.setTitulo(nuevoTitulo);

        int id=productoDTO.getId();
        productoService.update(productoDTO);

        ProductoDTO dt=productoService.findById(id);
        assertEquals(dt.getTitulo(),nuevoTitulo);
        assertEquals(dt.getDescripcion(),descripcion);
        assertEquals(dt.getCiudad().getId(),ciudad.getId());
        assertEquals(dt.getCategoria().getId(),categoria.getId());
        assertEquals(dt.isDisponible(),disponible);
    }



    @Test
    void findAll() {
        ProductoDTO productoDTO= new ProductoDTO("producto5","pro5",ciudad,categoria,true);
        productoService.create(productoDTO);
        PaginaDTO<ProductoDTO> productoDTOList=productoService.findAll(null,null);
        assertNotNull(productoDTOList);
        assertTrue(productoDTOList.getCantidad()>0);
    }


    @Test
    void findAllWithPages() {
        ProductoDTO productoDTO= new ProductoDTO("producto5","pro5",ciudad,categoria,true);
        productoService.create(productoDTO);
        PaginaDTO<ProductoDTO> productoDTOList=productoService.findAll(0, 20);
        assertNotNull(productoDTOList);
        assertTrue(productoDTOList.getCantidad()>0);
    }


    @Test
    void findByCiudadIdWithPages() {
        ProductoDTO productoDTO= new ProductoDTO("producto5","pro5",ciudad,categoria,true);
        productoService.create(productoDTO);
        int id= ciudad.getId();
        PaginaDTO<ProductoDTO>  paginaDTO=productoService.findByCiudadId(id,0,10);
        assertNotNull(paginaDTO);
        assertTrue(paginaDTO.getCantidad()>0);
        paginaDTO.getResultados().forEach(productoDTO1 -> assertEquals(productoDTO1.getCiudad().getId(),id));
    }

    @Test
    void findByCategoriaId() {
        ProductoDTO productoDTO= new ProductoDTO("producto5","pro5",ciudad,categoria,true);
        productoService.create(productoDTO);
        int id= categoria.getId();
        PaginaDTO<ProductoDTO> paginaDTO = productoService.findByCategoriaId(id,0,10);
        assertNotNull(paginaDTO);
        assertTrue(paginaDTO.getCantidad()>0);
        paginaDTO.getResultados().forEach(productoDTO1 -> assertEquals(productoDTO1.getCategoria().getId(),id));
    }

    @Test
    void findByCategoriaIdWithPages() {
        ProductoDTO productoDTO= new ProductoDTO("producto5","pro5",ciudad,categoria,true);
        productoService.create(productoDTO);
        int id= categoria.getId();
        PaginaDTO<ProductoDTO> productoDTOList=productoService.findByCategoriaId(id,0,10);
        assertNotNull(productoDTOList);
        assertTrue(productoDTOList.getCantidad()>0);
        productoDTOList.getResultados().forEach(productoDTO1 -> assertEquals(productoDTO1.getCategoria().getId(),id));
    }

    //Mapers
    public Ciudad mapEntity(CiudadDTO ciudadDTO){
        return mapper.convertValue(ciudadDTO, Ciudad.class);
    }
    public Categoria mapEntity(CategoriaDTO categoriaDTO){
        return mapper.convertValue(categoriaDTO, Categoria.class);
    }
}