package com.upn.ecommerce.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.upn.ecommerce.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
  @Query(name = "Usuario.findByUsername")
  Optional<Usuario> findByUsername(@Param("username") String username);
}