package com.digitalbooking.backend.Controllers;

import com.digitalbooking.backend.Dto.CaracteristicaDTO;
import com.digitalbooking.backend.Dto.PaginaDTO;
import com.digitalbooking.backend.Services.impl.CaracteristicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@CrossOrigin
@RestController
@RequestMapping("/caracteristicas")
public class CaracteristicaController {

    @Autowired
    private CaracteristicaService caracteristicaService;

    @GetMapping("{id}")
    public ResponseEntity<CaracteristicaDTO> findById(@PathVariable("id")Integer id){
        CaracteristicaDTO caracteristica = caracteristicaService.findById(id);
        return new ResponseEntity<>(caracteristica, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<CaracteristicaDTO> create(@RequestBody CaracteristicaDTO caracteristicaDTO){
        CaracteristicaDTO respuestaCaracteristica = caracteristicaService.create(caracteristicaDTO);
        return new ResponseEntity<>(respuestaCaracteristica, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public ResponseEntity<CaracteristicaDTO> update(@RequestBody CaracteristicaDTO caracteristicaDTO){
        CaracteristicaDTO newCaracteristicaDTO = caracteristicaService.update(caracteristicaDTO);
        return new ResponseEntity<>(newCaracteristicaDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id")Integer id){
        caracteristicaService.deleteById(id);
        return new ResponseEntity<>("Caracteristica Eliminada", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PaginaDTO<CaracteristicaDTO>> findAll(
            @RequestParam(value = "pagina", required = false)Integer page,
            @RequestParam(value = "tamanio", required = false)Integer size,
            HttpServletRequest request
    ){
        PaginaDTO<CaracteristicaDTO> paginaCaracteristicas = caracteristicaService.findAll(page, size);
        paginaCaracteristicas.setUrlBase(request.getRequestURL().toString());
        return new ResponseEntity<>(paginaCaracteristicas, HttpStatus.OK);
    }
}
