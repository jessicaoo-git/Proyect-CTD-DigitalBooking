package com.digitalbooking.backend.Repository;

import com.digitalbooking.backend.Models.Imagen;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IImagenRepository extends JpaRepository<Imagen,Integer> {

    Page<Imagen> findAll(Pageable pageable);
}
