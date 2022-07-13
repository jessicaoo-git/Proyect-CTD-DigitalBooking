package com.digitalbooking.backend.Security.repository;

import com.digitalbooking.backend.Security.entity.Rol;
import com.digitalbooking.backend.Security.enums.RolNombre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {

    Optional<Rol> findByRolNombre(RolNombre rolNombre);

}
