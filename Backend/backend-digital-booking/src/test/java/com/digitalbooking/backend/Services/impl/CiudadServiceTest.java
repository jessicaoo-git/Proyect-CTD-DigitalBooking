package com.digitalbooking.backend.Services.impl;

import com.digitalbooking.backend.Dto.CiudadDTO;
import com.digitalbooking.backend.Dto.PaginaDTO;
import com.digitalbooking.backend.Services.ICiudadService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CiudadServiceTest {

    @Autowired
    public ICiudadService ciudadService;

    @Test
    void create() {
        CiudadDTO ciudadDTO = new CiudadDTO("ciudad0");
        ciudadDTO = ciudadService.create(ciudadDTO);
        assertNotNull(ciudadDTO);
        assertTrue(ciudadDTO.getId() > 0);
    }

    @Test
    void update() {
        CiudadDTO ciudadDTO = new CiudadDTO("ciudad1");
        ciudadDTO = ciudadService.create(ciudadDTO);
        assertNotNull(ciudadDTO);
        assertTrue(ciudadDTO.getId() > 0);

        String nuevoNombre = "ciudad2";
        ciudadDTO.setNombre(nuevoNombre);
        ciudadService.update(ciudadDTO);
        CiudadDTO dt = ciudadService.findById(ciudadDTO.getId());
        assertEquals(dt.getNombre(), nuevoNombre);
    }

    @Test
    void findById() {
        CiudadDTO ciudadDTO = new CiudadDTO("ciudad3");
        ciudadDTO = ciudadService.create(ciudadDTO);
        assertNotNull(ciudadDTO);
        assertTrue(ciudadDTO.getId() > 0);
        CiudadDTO dt = ciudadService.findById(ciudadDTO.getId());
        assertNotNull(dt);
    }

    @Test
    void deleteById() {
        CiudadDTO ciudadDTO = new CiudadDTO("ciudad4");
        ciudadDTO = ciudadService.create(ciudadDTO);
        assertNotNull(ciudadDTO);
        assertTrue(ciudadDTO.getId() > 0);
        ciudadService.deleteById(ciudadDTO.getId());
        CiudadDTO dt = null;
        try {
            dt = ciudadService.findById(ciudadDTO.getId());
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("No se encontro la ciudad"));
        }
        assertNull(dt);
    }

    @Test
    void findAll() {
        CiudadDTO ciudadDTO = new CiudadDTO("ciudad5");
        ciudadDTO = ciudadService.create(ciudadDTO);
        assertNotNull(ciudadDTO);
        assertTrue(ciudadDTO.getId() > 0);
        CiudadDTO dt = ciudadService.findById(ciudadDTO.getId());
        assertNotNull(dt);
        PaginaDTO<CiudadDTO> paginaDTO= ciudadService.findAll(null,null);
        assertTrue(paginaDTO.getResultados().size() > 0);
    }

    @Test
    void findAllWithPages() {
        CiudadDTO ciudadDTO = new CiudadDTO("ciudad5");
        ciudadDTO = ciudadService.create(ciudadDTO);
        assertNotNull(ciudadDTO);
        assertTrue(ciudadDTO.getId() > 0);
        CiudadDTO dt = ciudadService.findById(ciudadDTO.getId());
        assertNotNull(dt);
        PaginaDTO<CiudadDTO> paginaDTO= ciudadService.findAll(0,10);
        assertTrue(paginaDTO.getResultados().size() > 0);
    }

    @Test
    void findByNombre() {
        CiudadDTO ciudadDTO = new CiudadDTO("ciudad95");
        ciudadDTO = ciudadService.create(ciudadDTO);
        assertNotNull(ciudadDTO);
        assertTrue(ciudadDTO.getId() > 0);
        CiudadDTO dt = ciudadService.findById(ciudadDTO.getId());
        assertNotNull(dt);
        List<CiudadDTO> paginaDTO= ciudadService.findByNombre("ciudad95");
        assertTrue(paginaDTO.size() > 0);
    }
}