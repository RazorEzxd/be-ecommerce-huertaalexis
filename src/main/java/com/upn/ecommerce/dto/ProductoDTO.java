package com.upn.ecommerce.dto;

import java.math.BigDecimal;
import jakarta.validation.constraints.*;

public record ProductoDTO(Long id, @NotBlank String nombre, @NotBlank String categoria,
    @NotNull @DecimalMin("0.0") BigDecimal precio, @NotNull @Min(0) Integer stock,
    String marca, String codigoBarras) {}