package com.upn.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity @Table(name = "detalle_pedido")
@NamedQuery(name = "DetallePedido.findByPedidoId",
    query = "SELECT d FROM DetallePedido d WHERE d.pedido.id = :pedidoId")
@Getter @Setter @NoArgsConstructor
public class DetallePedido {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne @JoinColumn(name = "pedido_id")
  private Pedido pedido;

  @ManyToOne @JoinColumn(name = "producto_id")
  private Producto producto;

  private Integer cantidad;
  private BigDecimal precioUnitario;
  private BigDecimal subtotal;
}