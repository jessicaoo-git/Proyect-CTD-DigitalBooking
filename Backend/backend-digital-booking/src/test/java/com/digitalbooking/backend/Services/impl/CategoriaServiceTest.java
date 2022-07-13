package com.digitalbooking.backend.Services.impl;

import com.digitalbooking.backend.Dto.CategoriaDTO;
import com.digitalbooking.backend.Dto.PaginaDTO;
import com.digitalbooking.backend.Services.ICategoriaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CategoriaServiceTest {

    @Autowired
    public ICategoriaService categoriaService;

    @Test
    void findById() {
        CategoriaDTO categoriaDTO= new CategoriaDTO("categoria0","cat0","www.imagen0.com");
        categoriaDTO=categoriaService.create(categoriaDTO);
        assertNotNull(categoriaDTO);
        CategoriaDTO dt=categoriaService.findById(categoriaDTO.getId());
        assertNotNull(dt);
    }

    @Test
    void create() {

        CategoriaDTO categoriaDTO= new CategoriaDTO("categoria2","cat2","www.imagen2.com");
        categoriaDTO=categoriaService.create(categoriaDTO);
        assertNotNull(categoriaDTO);
        assertTrue(categoriaDTO.getId()>0);

    }

    @Test
    void deleteById() {
        CategoriaDTO categoriaDTO= new CategoriaDTO("categoria6","cat6","www.imagen6.com");
        categoriaDTO=categoriaService.create(categoriaDTO);
        int id= categoriaDTO.getId();
        categoriaService.deleteById(id);
        CategoriaDTO dt=null;
        try {
            dt = categoriaService.findById(id);
        }
        catch (Exception e){
            assertTrue(e.getMessage().contains("No se encontro la categoria"));
        }
        assertNull(dt);
    }

    @Test
    void update() {

        String titulo="CategErick";
        String descripcion="catE";
        String urlImg="www.catE.com";

        CategoriaDTO categoriaDTO= new CategoriaDTO(titulo,descripcion,urlImg);
        categoriaDTO=categoriaService.create(categoriaDTO);

        String nuevoTitulo="Categoria3000";
        String nuevaDescripcion="catEN";
        String nuevaUrlImg="www.catEN.com";
        categoriaDTO.setTitulo(nuevoTitulo);
        categoriaDTO.setDescripcion(nuevaDescripcion);
        categoriaDTO.setUrlImagen(nuevaUrlImg);

        int id=categoriaDTO.getId();
        categoriaService.update(categoriaDTO);

        CategoriaDTO dt=categoriaService.findById(id);
        assertEquals(dt.getTitulo(),nuevoTitulo);
        assertEquals(dt.getDescripcion(),nuevaDescripcion);
        assertEquals(dt.getUrlImagen(),nuevaUrlImg);
    }


    @Test
    void findAll() {
        CategoriaDTO categoriaDTO= new CategoriaDTO("CategoriaV","catV","www.catv.com");
        categoriaService.create(categoriaDTO);
        PaginaDTO<CategoriaDTO> paginaDTO =categoriaService.findAll(null,null);
        assertNotNull(paginaDTO);
        assertTrue(paginaDTO.getResultados().size() > 0);
    }

    @Test
    void findAllWithPages() {
        CategoriaDTO categoriaDTO= new CategoriaDTO("CategoriaV","catV","www.catv.com");
        categoriaService.create(categoriaDTO);
        PaginaDTO<CategoriaDTO> paginaDTO=categoriaService.findAll(0,10);
        assertNotNull(paginaDTO);
        assertTrue(paginaDTO.getResultados().size() > 0);
    }
}