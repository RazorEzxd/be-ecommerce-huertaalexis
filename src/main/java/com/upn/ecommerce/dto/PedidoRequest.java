package com.upn.ecommerce.dto;

import java.util.List;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

public record PedidoRequest(@NotBlank String cliente, @NotBlank String metodoPago,
    @NotEmpty @Valid List<ItemDTO> items) {}