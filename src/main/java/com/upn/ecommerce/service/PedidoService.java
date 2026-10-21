package com.upn.ecommerce.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.upn.ecommerce.dto.*;
import com.upn.ecommerce.entity.*;
import com.upn.ecommerce.exception.*;
import com.upn.ecommerce.repository.*;
import lombok.RequiredArgsConstructor;

@Service @RequiredArgsConstructor
public class PedidoService {
  private final PedidoRepository pedidoRepo;
  private final ProductoRepository productoRepo;

  @Transactional
  public PedidoResponse crear(PedidoRequest req) {
    Pedido p = new Pedido();
    p.setCliente(req.cliente()); p.setMetodoPago(req.metodoPago());
    p.setFechaCompra(LocalDateTime.now()); p.setEstadoPedido("PENDIENTE");
    BigDecimal total = BigDecimal.ZERO;
    for (ItemDTO i : req.items()) {
      Producto pr = productoRepo.findById(i.productoId())
          .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado: " + i.productoId()));
      if (pr.getStock() < i.cantidad())
        throw new BadRequestException("Stock insuficiente para: " + pr.getNombre());
      pr.setStock(pr.getStock() - i.cantidad());
      DetallePedido d = new DetallePedido();
      d.setPedido(p); d.setProducto(pr); d.setCantidad(i.cantidad());
      d.setPrecioUnitario(pr.getPrecio());
      d.setSubtotal(pr.getPrecio().multiply(BigDecimal.valueOf(i.cantidad())));
      p.getDetalles().add(d);
      total = total.add(d.getSubtotal());
    }
    p.setMontoTotal(total);
    return toResponse(pedidoRepo.save(p));
  }

  @Transactional(readOnly = true)
  public PedidoResponse obtener(Long id) {
    return toResponse(pedidoRepo.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado: " + id)));
  }

  private PedidoResponse toResponse(Pedido p) {
    return new PedidoResponse(p.getId(), p.getCliente(), p.getFechaCompra(), p.getMontoTotal(),
        p.getEstadoPedido(), p.getMetodoPago(),
        p.getDetalles().stream().map(d -> new DetalleResponse(d.getProducto().getId(),
            d.getProducto().getNombre(), d.getCantidad(), d.getPrecioUnitario(), d.getSubtotal())).toList());
  }
}