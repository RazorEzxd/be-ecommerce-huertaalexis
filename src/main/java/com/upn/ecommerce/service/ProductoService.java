package com.upn.ecommerce.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.upn.ecommerce.dto.ProductoDTO;
import com.upn.ecommerce.entity.Producto;
import com.upn.ecommerce.exception.ResourceNotFoundException;
import com.upn.ecommerce.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;

@Service @RequiredArgsConstructor
public class ProductoService {
  private final ProductoRepository repo;

  public List<ProductoDTO> listar() { return repo.findAll().stream().map(this::toDTO).toList(); }
  public ProductoDTO obtener(Long id) { return toDTO(buscar(id)); }
  public ProductoDTO crear(ProductoDTO d) { return toDTO(repo.save(copiar(new Producto(), d))); }
  public ProductoDTO actualizar(Long id, ProductoDTO d) { return toDTO(repo.save(copiar(buscar(id), d))); }
  public void eliminar(Long id) { repo.delete(buscar(id)); }

  private Producto buscar(Long id) {
    return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado: " + id));
  }
  private Producto copiar(Producto p, ProductoDTO d) {
    p.setNombre(d.nombre()); p.setCategoria(d.categoria()); p.setPrecio(d.precio());
    p.setStock(d.stock()); p.setMarca(d.marca()); p.setCodigoBarras(d.codigoBarras());
    return p;
  }
  private ProductoDTO toDTO(Producto p) {
    return new ProductoDTO(p.getId(), p.getNombre(), p.getCategoria(), p.getPrecio(),
        p.getStock(), p.getMarca(), p.getCodigoBarras());
  }
}