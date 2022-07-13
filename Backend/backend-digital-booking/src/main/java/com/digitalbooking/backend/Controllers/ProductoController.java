package com.digitalbooking.backend.Controllers;

import com.digitalbooking.backend.Dto.PaginaDTO;
import com.digitalbooking.backend.Dto.ProductoDTO;
import com.digitalbooking.backend.Filtros.FiltroProductos;
import com.digitalbooking.backend.Services.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@CrossOrigin
@RestController
@RequestMapping("/productos")
public class ProductoController {
    @Autowired
    private IProductoService productoService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> findById(@PathVariable("id")Integer id){
        ProductoDTO productobuscada = productoService.findById(id);
        return new ResponseEntity<>(productobuscada, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ProductoDTO> create(@RequestBody ProductoDTO productodto){
        ProductoDTO respuestaProducto = productoService.create(productodto);
        return new ResponseEntity<>(respuestaProducto, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public ResponseEntity<ProductoDTO> update(@RequestBody ProductoDTO productoDTO){
        ProductoDTO newProductoDTO = productoService.update(productoDTO);
        return new ResponseEntity<>(newProductoDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id")Integer id){
        productoService.deleteById(id);
        return new ResponseEntity<>("Producto Eliminado", HttpStatus.OK);
    }

    //Paginas a partir de pagina=0 no de 1
    @GetMapping
    public ResponseEntity<PaginaDTO<ProductoDTO>> findAll(
            @RequestParam(value = "pagina", required = false)Integer page,
            @RequestParam(value = "tamanio", required = false)Integer size,
            @RequestParam(value = "ciudad_id", required = false)Integer ciudadId,
            @RequestParam(value = "categoria_id", required = false)Integer categoriaId,
            @RequestParam(value = "fecha_inicio", required = false)@DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicio,
            @RequestParam(value = "fecha_fin", required = false)@DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFin,
            HttpServletRequest request
    ) {

        PaginaDTO<ProductoDTO> paginaProductos;

        FiltroProductos filtros = new FiltroProductos();
        if(ciudadId!=null) filtros.setCiudadId(ciudadId);
        if(categoriaId!=null) filtros.setCategoriaId(categoriaId);
        if(fechaInicio!=null && fechaFin!=null){
            filtros.setFechaInicio(fechaInicio);
            filtros.setFechaFin(fechaFin);
        }
        paginaProductos = productoService.findByFilters(filtros, page, size);

        String url= request.getRequestURL().toString();
        paginaProductos.setUrlBase(url);

        return new ResponseEntity<>(paginaProductos, HttpStatus.OK);
    }

}
