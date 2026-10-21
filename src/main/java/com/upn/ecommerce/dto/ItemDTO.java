package com.upn.ecommerce.dto;

import jakarta.validation.constraints.*;

public record ItemDTO(@NotNull Long productoId, @NotNull @Min(1) Integer cantidad) {}