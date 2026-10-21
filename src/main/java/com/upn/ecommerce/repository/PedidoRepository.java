package com.upn.ecommerce.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.upn.ecommerce.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
  @Query(name = "Pedido.findByCliente")
  List<Pedido> findByCliente(@Param("cliente") String cliente);
}