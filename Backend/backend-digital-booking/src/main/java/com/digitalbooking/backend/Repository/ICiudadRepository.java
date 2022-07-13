package com.digitalbooking.backend.Repository;

import com.digitalbooking.backend.Models.Ciudad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICiudadRepository extends JpaRepository<Ciudad,Integer> {
    Page<Ciudad> findAll(Pageable pageable);

    @Query("select c from Ciudad c where c.nombre like %?1%")
    List<Ciudad>findByNombre(String fragment);

}
