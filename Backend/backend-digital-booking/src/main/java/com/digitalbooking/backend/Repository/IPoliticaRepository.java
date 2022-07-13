package com.digitalbooking.backend.Repository;

import com.digitalbooking.backend.Models.Politica;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPoliticaRepository extends JpaRepository<Politica,Integer> {
    Page<Politica> findAll(Pageable pageable);
}
