package com.digitalbooking.backend.Controllers;

import com.digitalbooking.backend.Dto.PaginaDTO;
import com.digitalbooking.backend.Dto.TituloPoliticaDTO;
import com.digitalbooking.backend.Services.impl.TituloPoliticaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping("/titulos-politicas")
public class TituloPoliticaController {

    @Autowired
    private TituloPoliticaService tituloPoliticaService;

    @GetMapping("{id}")
    public ResponseEntity<TituloPoliticaDTO> findById(@PathVariable("id")Integer id){
        TituloPoliticaDTO tituloPolitica = tituloPoliticaService.findById(id);
        return new ResponseEntity<>(tituloPolitica, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PaginaDTO<TituloPoliticaDTO>> findAll(
            @RequestParam(value = "pagina", required = false)Integer page,
            @RequestParam(value = "tamanio", required = false)Integer size,
            HttpServletRequest request
    ){
        PaginaDTO<TituloPoliticaDTO> paginaTituloPoliticas = tituloPoliticaService.findAll(page, size);
        paginaTituloPoliticas.setUrlBase(request.getRequestURL().toString());
        return new ResponseEntity<>(paginaTituloPoliticas, HttpStatus.OK);
    }
}