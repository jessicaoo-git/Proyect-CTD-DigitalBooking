package com.digitalbooking.backend.Services.impl;

import com.digitalbooking.backend.Dto.PaginaDTO;
import com.digitalbooking.backend.Dto.PoliticaDTO;
import com.digitalbooking.backend.Services.IPoliticaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PoliticaServiceTest {

    @Autowired
    public IPoliticaService politicaService;

    /*
    @Test
    void create() {
        PoliticaDTO politicaDTO= new PoliticaDTO("politica1");
        politicaDTO=politicaService.create(politicaDTO);
        assertNotNull(politicaDTO);
        assertTrue(politicaDTO.getId()>0);
    }

    @Test
    void update() {
        PoliticaDTO politicaDTO= new PoliticaDTO("politica1");
        politicaDTO=politicaService.create(politicaDTO);
        assertNotNull(politicaDTO);
        assertTrue(politicaDTO.getId()>0);
        politicaDTO.setTitulo("politica2");
        PoliticaDTO dt=politicaService.update(politicaDTO);
        assertNotNull(dt);
        assertEquals(politicaDTO.getTitulo(), dt.getTitulo());
    }

    @Test
    void findById() {
        PoliticaDTO politicaDTO= new PoliticaDTO("politica1");
        politicaDTO=politicaService.create(politicaDTO);
        assertNotNull(politicaDTO);
        assertTrue(politicaDTO.getId()>0);
        PoliticaDTO dt=politicaService.findById(politicaDTO.getId());
        assertNotNull(dt);
    }

    @Test
    void deleteById() {
        PoliticaDTO politicaDTO= new PoliticaDTO("politica1");
        politicaDTO=politicaService.create(politicaDTO);
        int id= politicaDTO.getId();
        politicaService.deleteById(id);
        PoliticaDTO dt=null;
        try {
            dt = politicaService.findById(id);
        }catch (Exception e){
            assertTrue(e.getMessage().contains("No se encontro"));
        }
        assertNull(dt);
    }

    @Test
    void findAll() {
        PoliticaDTO politicaDTO = new PoliticaDTO("politica5");
        politicaDTO = politicaService.create(politicaDTO);
        assertNotNull(politicaDTO);
        assertTrue(politicaDTO.getId() > 0);
        PoliticaDTO dt = politicaService.findById(politicaDTO.getId());
        assertNotNull(dt);
        PaginaDTO<PoliticaDTO> pagina = politicaService.findAll(null,null);
        assertTrue(pagina.getResultados().size() > 0);
    }

    @Test
    void findAllWithPages() {
        PoliticaDTO politicaDTO = new PoliticaDTO("politica5");
        politicaDTO = politicaService.create(politicaDTO);
        assertNotNull(politicaDTO);
        assertTrue(politicaDTO.getId() > 0);
        PoliticaDTO dt = politicaService.findById(politicaDTO.getId());
        assertNotNull(dt);
        PaginaDTO<PoliticaDTO> pagina = politicaService.findAll(0,10);
        assertTrue(pagina.getResultados().size() > 0);
    }
    
*/
}