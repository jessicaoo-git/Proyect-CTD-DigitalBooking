package com.digitalbooking.backend.Services.impl;

import com.digitalbooking.backend.Dto.*;
import com.digitalbooking.backend.Models.Categoria;
import com.digitalbooking.backend.Models.Ciudad;
import com.digitalbooking.backend.Models.Producto;
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
class ImagenServiceTest {

    @Autowired
    public ImagenService imagenService;

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
    private Producto producto;

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
        ProductoDTO productoDTO= new ProductoDTO("producto1","pro1",ciudad
                ,categoria,true);
        this.producto=mapEntity(productoService.create(productoDTO));
    }

    @Test
    void create() {
        ImagenDTO imagenDTO= new ImagenDTO("car0","www.imagen0.com",producto);
        imagenDTO=imagenService.create(imagenDTO);
        assertNotNull(imagenDTO);
        assertTrue(imagenDTO.getId()>0);
    }

    /*
    @Test
    void update() {
        ImagenDTO imagenDTO= new ImagenDTO("car0","www.imagen0.com",producto);
        imagenDTO=imagenService.create(imagenDTO);
        assertNotNull(imagenDTO);
        assertTrue(imagenDTO.getId()>0);

        String titulonuevo="carErick";
        String urlNueva="carErick.com";

        imagenDTO.setTitulo(titulonuevo);
        imagenDTO.setUrl(urlNueva);

        imagenService.update(imagenDTO);

        ImagenDTO dt=imagenService.findById(imagenDTO.getId());
        assertNotNull(dt);
        assertEquals(titulonuevo,dt.getTitulo());
        assertEquals(urlNueva,dt.getUrl());
    }*/

    @Test
    void findById() {
        ImagenDTO imagenDTO= new ImagenDTO("car0","www.imagen0.com",producto);
        imagenDTO=imagenService.create(imagenDTO);
        assertNotNull(imagenDTO);
        assertTrue(imagenDTO.getId()>0);
        ImagenDTO dt=imagenService.findById(imagenDTO.getId());
        assertNotNull(dt);
    }

    @Test
    void deleteById() {
        ImagenDTO imagenDTO= new ImagenDTO("car6","www.imagen6.com",producto);
        imagenDTO=imagenService.create(imagenDTO);
        int id= imagenDTO.getId();
        imagenService.deleteById(id);
        ImagenDTO dt=null;
        try {
            dt = imagenService.findById(id);
        }
        catch (Exception e){
            assertTrue(e.getMessage().contains("No se encontro"));
        }
        assertNull(dt);
    }

    @Test
    void findAll() {
        ImagenDTO imagenDTO = new ImagenDTO("asde","imagen5",producto);
        imagenDTO = imagenService.create(imagenDTO);
        assertNotNull(imagenDTO);
        assertTrue(imagenDTO.getId() > 0);
        ImagenDTO dt = imagenService.findById(imagenDTO.getId());
        assertNotNull(dt);
        PaginaDTO<ImagenDTO> paginaDTO = imagenService.findAll(null,null);
        assertTrue(paginaDTO.getResultados().size() > 0);
    }

    @Test
    void findAllWithPages() {
        ImagenDTO imagenDTO = new ImagenDTO("asde","imagen5",producto);
        imagenDTO = imagenService.create(imagenDTO);
        assertNotNull(imagenDTO);
        assertTrue(imagenDTO.getId() > 0);
        ImagenDTO dt = imagenService.findById(imagenDTO.getId());
        assertNotNull(dt);
        PaginaDTO<ImagenDTO> paginaDTO = imagenService.findAll(0,10);
        assertTrue(paginaDTO.getResultados().size() > 0);
    }

    //Mapers
    public Ciudad mapEntity(CiudadDTO ciudadDTO){
        return mapper.convertValue(ciudadDTO, Ciudad.class);
    }
    public Categoria mapEntity(CategoriaDTO categoriaDTO){
        return mapper.convertValue(categoriaDTO, Categoria.class);
    }
    public Producto mapEntity(ProductoDTO productoDTO){
        return mapper.convertValue(productoDTO, Producto.class);
    }

}