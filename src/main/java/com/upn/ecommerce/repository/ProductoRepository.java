package com.upn.ecommerce.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.upn.ecommerce.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
  @Query(name = "Producto.findByCategoria")
  List<Producto> findByCategoria(@Param("categoria") String categoria);

  @Query(name = "Producto.buscarPorNombre")
  List<Producto> buscarPorNombre(@Param("nombre") String nombre);
}