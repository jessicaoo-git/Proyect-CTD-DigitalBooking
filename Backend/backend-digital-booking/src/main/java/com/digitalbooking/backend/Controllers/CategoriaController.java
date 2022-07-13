package com.digitalbooking.backend.Controllers;

import com.digitalbooking.backend.Dto.CategoriaDTO;
import com.digitalbooking.backend.Dto.PaginaDTO;
import com.digitalbooking.backend.Services.impl.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping("/categorias")
public class CategoriaController {

   @Autowired
   private CategoriaService categoriaService;

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> findById(@PathVariable("id")Integer id){
        CategoriaDTO categoriabuscada = categoriaService.findById(id);
        return new ResponseEntity<>(categoriabuscada, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<CategoriaDTO> create(@RequestBody CategoriaDTO categoriadto){
        CategoriaDTO respuestaCategoria = categoriaService.create(categoriadto);
        return new ResponseEntity<>(respuestaCategoria, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public ResponseEntity<CategoriaDTO> update(@RequestBody CategoriaDTO categoriaDTO){
        CategoriaDTO newCategoriaDTO = categoriaService.update(categoriaDTO);
        return new ResponseEntity<>(newCategoriaDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id")Integer id){
        categoriaService.deleteById(id);
        return new ResponseEntity<>("Categoria eliminada", HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<PaginaDTO<CategoriaDTO>> findAll(
            @RequestParam(value = "pagina", required = false)Integer page,
            @RequestParam(value = "tamanio", required = false)Integer size,
            HttpServletRequest request
    ) {
        PaginaDTO<CategoriaDTO> paginaCategorias = categoriaService.findAll(page, size);
        paginaCategorias.setUrlBase(request.getRequestURL().toString());
        return new ResponseEntity<>(paginaCategorias, HttpStatus.OK);
    }



}
