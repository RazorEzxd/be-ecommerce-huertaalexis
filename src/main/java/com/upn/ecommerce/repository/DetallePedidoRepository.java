package com.upn.ecommerce.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.upn.ecommerce.entity.DetallePedido;

public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Long> {
  @Query(name = "DetallePedido.findByPedidoId")
  List<DetallePedido> findByPedidoId(@Param("pedidoId") Long pedidoId);
}