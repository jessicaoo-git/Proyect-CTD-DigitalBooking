package com.digitalbooking.backend.Controllers;


import com.digitalbooking.backend.Dto.PaginaDTO;
import com.digitalbooking.backend.Dto.ReservaDTO;
import com.digitalbooking.backend.Dto.ReservaDTOList;
import com.digitalbooking.backend.Services.impl.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    public ReservaService reservaService;

    @GetMapping("{id}")
    public ResponseEntity<ReservaDTO> findById(@PathVariable("id")Integer id){
        ReservaDTO reserva = reservaService.findById(id);
        return new ResponseEntity<>(reserva, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ResponseEntity<ReservaDTO> create(@RequestBody ReservaDTO reservaDTO){
        ReservaDTO respuestaReserva = reservaService.create(reservaDTO);
        return new ResponseEntity<>(respuestaReserva, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping
    public ResponseEntity<ReservaDTO> update(@RequestBody ReservaDTO reservaDTO){
        ReservaDTO newReservaDTO = reservaService.update(reservaDTO);
        return new ResponseEntity<>(newReservaDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id")Integer id){
        reservaService.deleteById(id);
        return new ResponseEntity<>("Reserva Eliminada", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PaginaDTO<?>> findAll(
            @RequestParam(value = "producto_id", required = false)Integer productoId,
            @RequestParam(value = "pagina", required = false)Integer page,
            @RequestParam(value = "tamanio", required = false)Integer size,
            @RequestParam(value = "usuario_id", required = false)Integer usuarioId,
            HttpServletRequest request
    ){
        PaginaDTO<?> paginaReservas;
        if(productoId!=null)
            paginaReservas= reservaService.findByProductoId(productoId, page, size);
        else if(usuarioId!=null)
            paginaReservas = reservaService.findByUsuarioId(usuarioId, page, size);
        else
            paginaReservas = reservaService.findAll(page, size);
        paginaReservas.setUrlBase(request.getRequestURL().toString());
        return new ResponseEntity<>(paginaReservas, HttpStatus.OK);
    }

}
