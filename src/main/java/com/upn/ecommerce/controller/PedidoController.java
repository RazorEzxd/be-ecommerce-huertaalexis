package com.upn.ecommerce.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.upn.ecommerce.dto.*;
import com.upn.ecommerce.service.PedidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController @RequestMapping("/api/pedidos") @RequiredArgsConstructor
public class PedidoController {
  private final PedidoService service;

  @PostMapping @ResponseStatus(HttpStatus.CREATED)
  public PedidoResponse crear(@Valid @RequestBody PedidoRequest r) { return service.crear(r); }
  @GetMapping("/{id}") public PedidoResponse obtener(@PathVariable Long id) { return service.obtener(id); }
}