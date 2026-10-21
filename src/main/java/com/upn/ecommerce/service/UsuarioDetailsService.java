package com.upn.ecommerce.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.upn.ecommerce.entity.Usuario;
import com.upn.ecommerce.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@Service @RequiredArgsConstructor
public class UsuarioDetailsService implements UserDetailsService {
  private final UsuarioRepository repo;

  @Override
  public UserDetails loadUserByUsername(String username) {
    Usuario u = repo.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    return User.withUsername(u.getUsername()).password(u.getPassword()).roles(u.getRol()).build();
  }
}