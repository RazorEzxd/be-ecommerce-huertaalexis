package com.upn.ecommerce.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.upn.ecommerce.dto.ProductoDTO;
import com.upn.ecommerce.service.ProductoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController @RequestMapping("/api/productos") @RequiredArgsConstructor
public class ProductoController {
  private final ProductoService service;

  @GetMapping public List<ProductoDTO> listar() { return service.listar(); }
  @GetMapping("/{id}") public ProductoDTO obtener(@PathVariable Long id) { return service.obtener(id); }
  @PostMapping @ResponseStatus(HttpStatus.CREATED)
  public ProductoDTO crear(@Valid @RequestBody ProductoDTO d) { return service.crear(d); }
  @PutMapping("/{id}")
  public ProductoDTO actualizar(@PathVariable Long id, @Valid @RequestBody ProductoDTO d) { return service.actualizar(id, d); }
  @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
  public void eliminar(@PathVariable Long id) { service.eliminar(id); }
}