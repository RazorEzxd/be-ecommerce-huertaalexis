package com.upn.ecommerce.entity;

import java.time.LocalDate;
import lombok.*;
import java.math.BigDecimal;
import jakarta.persistence.*;

@Entity @Table(name = "productos")
@NamedQueries({
  @NamedQuery(name = "Producto.findByCategoria",
      query = "SELECT p FROM Producto p WHERE p.categoria = :categoria"),
  @NamedQuery(name = "Producto.buscarPorNombre",
      query = "SELECT p FROM Producto p WHERE LOWER(p.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
})
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Producto {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nombre;
  private String categoria;
  private BigDecimal precioCompra;
  private BigDecimal precioVenta;
  private LocalDate fechaIngreso;
  private Integer stock;
  private String marca;
  private String codigoBarras;
}