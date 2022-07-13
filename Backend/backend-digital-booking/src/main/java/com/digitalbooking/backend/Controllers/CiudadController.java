package com.digitalbooking.backend.Controllers;


import com.digitalbooking.backend.Dto.CiudadDTO;
import com.digitalbooking.backend.Dto.PaginaDTO;
import com.digitalbooking.backend.Services.ICiudadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/ciudades")
public class CiudadController  {
    @Autowired
    private ICiudadService ciudadService;

    @GetMapping("/{id}")
    public ResponseEntity<CiudadDTO> findById(@PathVariable("id")Integer id){
        CiudadDTO ciudadbuscada = ciudadService.findById(id);
        return new ResponseEntity<>(ciudadbuscada, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<CiudadDTO> create(@RequestBody CiudadDTO ciudaddto){
        CiudadDTO respuestaCiudad = ciudadService.create(ciudaddto);
        return new ResponseEntity<>(respuestaCiudad, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public ResponseEntity<CiudadDTO> update(@RequestBody CiudadDTO ciudadDTO){
        CiudadDTO newCiudadDTO = ciudadService.update(ciudadDTO);
        return new ResponseEntity<>(newCiudadDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id")Integer id){
        ciudadService.deleteById(id);
        return new ResponseEntity<>("Ciudad Eliminada", HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<PaginaDTO<CiudadDTO>> findAll(
            @RequestParam(value = "pagina", required = false)Integer page,
            @RequestParam(value = "tamanio", required = false)Integer size,
            HttpServletRequest request
    ) {
        PaginaDTO<CiudadDTO> paginaCiudades = ciudadService.findAll(page, size);
        paginaCiudades.setUrlBase(request.getRequestURL().toString());
        return new ResponseEntity<>(paginaCiudades, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("SaveAll")
    public ResponseEntity<List<CiudadDTO>> saveAll(@RequestBody List<CiudadDTO> ciudades){
        List<CiudadDTO> listaCiudad = ciudadService.guardarTodos(ciudades);
        return new ResponseEntity<>(listaCiudad, HttpStatus.OK);
    }

    @GetMapping("/porNombre/{nombre}")
    public ResponseEntity<List<CiudadDTO>> findByNombre(@PathVariable("nombre")String name){
        List<CiudadDTO> ciudades = ciudadService.findByNombre(name);
        return new ResponseEntity<>(ciudades, HttpStatus.OK);
    }

}
