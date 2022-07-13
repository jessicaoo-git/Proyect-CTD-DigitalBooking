package com.digitalbooking.backend.Repository;

import com.digitalbooking.backend.Models.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoriaRepository  extends JpaRepository<Categoria,Integer> {
    Page<Categoria> findAll(Pageable pageable);
}