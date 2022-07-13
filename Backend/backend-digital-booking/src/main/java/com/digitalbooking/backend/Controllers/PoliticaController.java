package com.digitalbooking.backend.Controllers;

import com.digitalbooking.backend.Dto.PaginaDTO;
import com.digitalbooking.backend.Dto.PoliticaDTO;
import com.digitalbooking.backend.Services.impl.PoliticaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@CrossOrigin
@RestController
@RequestMapping("/politicas")
public class PoliticaController  {
    @Autowired
    private PoliticaService politicaService;

    @GetMapping("{id}")
    public ResponseEntity<PoliticaDTO> findById(@PathVariable("id")Integer id){
        PoliticaDTO politica = politicaService.findById(id);
        return new ResponseEntity<>(politica, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PoliticaDTO> create(@RequestBody PoliticaDTO politicaDTO){
        PoliticaDTO respuestaPolitica = politicaService.create(politicaDTO);
        return new ResponseEntity<>(respuestaPolitica, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public ResponseEntity<PoliticaDTO> update(@RequestBody PoliticaDTO politicaDTO){
        PoliticaDTO newPoliticaDTO = politicaService.update(politicaDTO);
        return new ResponseEntity<>(newPoliticaDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id")Integer id){
        politicaService.deleteById(id);
        return new ResponseEntity<>("Politica Eliminada", HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<PaginaDTO<PoliticaDTO>> findAll(
            @RequestParam(value = "pagina", required = false)Integer page,
            @RequestParam(value = "tamanio", required = false)Integer size,
            HttpServletRequest request
    ){
        PaginaDTO<PoliticaDTO> paginaPoliticas = politicaService.findAll(page, size);
        paginaPoliticas.setUrlBase(request.getRequestURL().toString());
        return new ResponseEntity<>(paginaPoliticas, HttpStatus.OK);
    }
}
