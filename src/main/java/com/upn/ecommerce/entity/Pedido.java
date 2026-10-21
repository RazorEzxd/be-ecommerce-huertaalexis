package com.upn.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Entity @Table(name = "pedidos")
@NamedQuery(name = "Pedido.findByCliente",
    query = "SELECT p FROM Pedido p WHERE p.cliente = :cliente")
@Getter @Setter @NoArgsConstructor
public class Pedido {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String cliente;
  private LocalDateTime fechaCompra;
  private BigDecimal montoTotal;
  private String estadoPedido;
  private String metodoPago;

  @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
  private List<DetallePedido> detalles = new ArrayList<>();
}