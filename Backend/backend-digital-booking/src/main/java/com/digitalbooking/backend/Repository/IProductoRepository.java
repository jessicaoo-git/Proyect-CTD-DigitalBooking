package com.digitalbooking.backend.Repository;


import com.digitalbooking.backend.Models.Producto;
import com.digitalbooking.backend.Repository.custom.CustomProductoRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface IProductoRepository extends JpaRepository<Producto,Integer>, CustomProductoRepository {
     Page<Producto> findByCiudad_Id(Integer id,Pageable pageable);
     Page<Producto> findByCategoria_Id(Integer id,Pageable pageable);
     Page<Producto> findAll(Pageable pageable);
     @EntityGraph(attributePaths = {"categoria", "ciudad", "imagenes", "caracteristicas"})
     @Query(value="select p from Producto as p join p.imagenes as i where i.id in (select max(im.id) from Imagen as im group by im.producto.id) ")
     Page<Producto> findAllWithOneImage(Pageable pageable);

}
