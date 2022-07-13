package com.digitalbooking.backend.Security.repository;

import com.digitalbooking.backend.Models.Producto;
import com.digitalbooking.backend.Security.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
    Optional<Usuario> findByNombreUsuario(String nombreUsuario);
    boolean existsByNombreUsuario(String nombreUsuario);
    boolean existsByEmail(String email);
    @Query("SELECT u FROM Usuario u WHERE u.verificationCode = ?1")
    public Usuario findByVerificationCode(String code);
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM favoritos WHERE usuario_id = ?1 and producto_id = ?2", nativeQuery = true)
    public Integer deleteFavorito(Integer usuarioId, Integer productoId);
    @Query(value="SELECT u.productos from Usuario as u where u.id = ?1")
    Page<Producto> findAllfavoritos(Integer id, Pageable pageable);
    @Query(value = "SELECT producto_id FROM favoritos WHERE usuario_id = ?1 and producto_id = ?2", nativeQuery = true)
    public Integer findFavorito(Integer usuarioId, Integer productoId);
}
