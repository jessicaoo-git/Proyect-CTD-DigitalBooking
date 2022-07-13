package com.digitalbooking.backend.Repository;

import com.digitalbooking.backend.Models.TituloPolitica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITituloPoliticaRepository extends JpaRepository<TituloPolitica, Integer> {
}
