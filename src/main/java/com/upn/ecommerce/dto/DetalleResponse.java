package com.upn.ecommerce.dto;

import java.math.BigDecimal;

public record DetalleResponse(Long productoId, String producto, Integer cantidad,
    BigDecimal precioUnitario, BigDecimal subtotal) {}
