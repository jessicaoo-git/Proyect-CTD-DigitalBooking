package com.digitalbooking.backend.Controllers;


import com.digitalbooking.backend.Dto.ImagenDTO;
import com.digitalbooking.backend.Dto.PaginaDTO;
import com.digitalbooking.backend.Services.impl.ImagenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@CrossOrigin
@RestController
@RequestMapping("/imagenes")
public class ImagenController  {
    @Autowired
    private ImagenService imagenService;

    @GetMapping("{id}")
    public ResponseEntity<ImagenDTO> findById(@PathVariable("id")Integer id){
        ImagenDTO imagen = imagenService.findById(id);
        return new ResponseEntity<>(imagen, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ImagenDTO> create(@RequestBody ImagenDTO imagenDTO){
        ImagenDTO respuestaImagen = imagenService.create(imagenDTO);
        return new ResponseEntity<>(respuestaImagen, HttpStatus.OK);
    }
    /*
    @PutMapping
    public ResponseEntity<ImagenDTO> update(@RequestBody ImagenDTO imagenDTO){
        ImagenDTO newImagenDTO = imagenService.update(imagenDTO);
        return new ResponseEntity<>(newImagenDTO, HttpStatus.OK);
    }*/

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id")Integer id){
        imagenService.deleteById(id);
        return new ResponseEntity<>("Imagen Eliminada", HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<PaginaDTO<ImagenDTO>> findAll(
            @RequestParam(value = "pagina", required = false)Integer page,
            @RequestParam(value = "tamanio", required = false)Integer size,
            HttpServletRequest request
    ){
        PaginaDTO<ImagenDTO> paginaImagenes  = imagenService.findAll(page, size);
        paginaImagenes.setUrlBase(request.getRequestURL().toString());
        return new ResponseEntity<>(paginaImagenes, HttpStatus.OK);
    }
}
