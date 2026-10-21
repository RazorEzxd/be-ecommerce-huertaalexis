package com.upn.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "usuarios")
@NamedQuery(name = "Usuario.findByUsername",
    query = "SELECT u FROM Usuario u WHERE u.username = :username")
@Getter @Setter @NoArgsConstructor
public class Usuario {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String username;
  private String password;
  private String rol;
}