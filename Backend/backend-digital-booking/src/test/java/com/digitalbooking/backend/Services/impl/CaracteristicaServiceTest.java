package com.digitalbooking.backend.Services.impl;

import com.digitalbooking.backend.Dto.CaracteristicaDTO;
import com.digitalbooking.backend.Dto.PaginaDTO;
import com.digitalbooking.backend.Services.ICaracteristicaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CaracteristicaServiceTest {

    @Autowired
    public ICaracteristicaService caracteristicaService;

    @Test
    void create() {
        CaracteristicaDTO caracteristicaDTO= new CaracteristicaDTO("car0","www.imagen0.com");
        caracteristicaDTO=caracteristicaService.create(caracteristicaDTO);
        assertNotNull(caracteristicaDTO);
        assertTrue(caracteristicaDTO.getId()>0);
    }

    @Test
    void update() {
        CaracteristicaDTO caracteristicaDTO= new CaracteristicaDTO("car0","www.imagen0.com");
        caracteristicaDTO=caracteristicaService.create(caracteristicaDTO);
        assertNotNull(caracteristicaDTO);
        assertTrue(caracteristicaDTO.getId()>0);

        String titulonuevo="carErick";
        String urlNueva="descerick.com";

        caracteristicaDTO.setTitulo(titulonuevo);
        caracteristicaDTO.setUrlImagen(urlNueva);

        caracteristicaService.update(caracteristicaDTO);

        CaracteristicaDTO dt=caracteristicaService.findById(caracteristicaDTO.getId());
        assertNotNull(dt);
        assertEquals(titulonuevo,dt.getTitulo());
        assertEquals(urlNueva,dt.getUrlImagen());

    }

    @Test
    void findById() {
        CaracteristicaDTO caracteristicaDTO= new CaracteristicaDTO("car0","www.imagen0.com");
        caracteristicaDTO=caracteristicaService.create(caracteristicaDTO);
        assertNotNull(caracteristicaDTO);
        assertTrue(caracteristicaDTO.getId()>0);
        CaracteristicaDTO dt=caracteristicaService.findById(caracteristicaDTO.getId());
        assertNotNull(dt);
    }

    @Test
    void deleteById() {
        CaracteristicaDTO caracteristicaDTO= new CaracteristicaDTO("car6","www.imagen6.com");
        caracteristicaDTO=caracteristicaService.create(caracteristicaDTO);
        int id= caracteristicaDTO.getId();
        caracteristicaService.deleteById(id);
        CaracteristicaDTO dt=null;
        try {
            dt = caracteristicaService.findById(id);
        }
        catch (Exception e){
            assertTrue(e.getMessage().contains("No se encontro"));
        }
        assertNull(dt);
    }

    @Test
    void findAll() {
        CaracteristicaDTO caracteristicaDTO = new CaracteristicaDTO("caracteristica5","asdas");
        caracteristicaDTO = caracteristicaService.create(caracteristicaDTO);
        assertNotNull(caracteristicaDTO);
        assertTrue(caracteristicaDTO.getId() > 0);
        CaracteristicaDTO dt = caracteristicaService.findById(caracteristicaDTO.getId());
        assertNotNull(dt);
        PaginaDTO<CaracteristicaDTO> paginaDTO = caracteristicaService.findAll(null,null);
        assertTrue(paginaDTO.getResultados().size() > 0);
    }


    @Test
    void findAllWithPages() {
        CaracteristicaDTO caracteristicaDTO = new CaracteristicaDTO("caracteristica5","asdas");
        caracteristicaDTO = caracteristicaService.create(caracteristicaDTO);
        assertNotNull(caracteristicaDTO);
        assertTrue(caracteristicaDTO.getId() > 0);
        CaracteristicaDTO dt = caracteristicaService.findById(caracteristicaDTO.getId());
        assertNotNull(dt);
        PaginaDTO<CaracteristicaDTO> paginaDTO= caracteristicaService.findAll(0,10);
        assertTrue(paginaDTO.getResultados().size() > 0);
    }
}