package com.digitalbooking.backend.Repository;

import com.digitalbooking.backend.Models.Caracteristica;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICaracteristicaRepository extends JpaRepository<Caracteristica,Integer> {
    Page<Caracteristica> findAll(Pageable pageable);
}
