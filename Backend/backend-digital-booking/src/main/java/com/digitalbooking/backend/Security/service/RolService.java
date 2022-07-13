package com.digitalbooking.backend.Security.service;

import com.digitalbooking.backend.Security.entity.Rol;
import com.digitalbooking.backend.Security.enums.RolNombre;
import com.digitalbooking.backend.Security.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    public Optional<Rol> findByRolNombre(RolNombre rolNombre) {
        return rolRepository.findByRolNombre(rolNombre);
    }

    public void save(Rol rol) {
        rolRepository.save(rol);
    }


}
