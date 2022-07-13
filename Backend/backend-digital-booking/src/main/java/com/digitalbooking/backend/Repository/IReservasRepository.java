package com.digitalbooking.backend.Repository;

import com.digitalbooking.backend.Models.Reserva;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReservasRepository extends JpaRepository<Reserva, Integer> {
    Page<Reserva> findAll(Pageable pageable);
    Page<Reserva> findByProductoId(Integer productoId, Pageable pageable);
    Page<Reserva> findByUsuarioId( Integer usuarioId, Pageable pageable);
}
