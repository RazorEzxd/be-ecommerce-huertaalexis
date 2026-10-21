package com.upn.ecommerce.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record PedidoResponse(Long id, String cliente, LocalDateTime fechaCompra,
    BigDecimal montoTotal, String estadoPedido, String metodoPago,
    List<DetalleResponse> detalles) {}