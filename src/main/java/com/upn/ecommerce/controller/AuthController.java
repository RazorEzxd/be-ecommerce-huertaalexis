package com.upn.ecommerce.controller;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.*;
import com.upn.ecommerce.entity.Usuario;
import com.upn.ecommerce.exception.BadRequestException;
import com.upn.ecommerce.repository.UsuarioRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;

@RestController @RequestMapping("/auth") @RequiredArgsConstructor
public class AuthController {
  private final AuthenticationManager authManager;
  private final JwtEncoder encoder;
  private final UsuarioRepository repo;
  private final PasswordEncoder passwordEncoder;

  public record AuthRequest(@NotBlank String username, @NotBlank String password) {}

  @PostMapping("/register") @ResponseStatus(HttpStatus.CREATED)
  public Map<String, String> register(@Valid @RequestBody AuthRequest r) {
    if (repo.findByUsername(r.username()).isPresent())
      throw new BadRequestException("El usuario ya existe");
    Usuario u = new Usuario();
    u.setUsername(r.username());
    u.setPassword(passwordEncoder.encode(r.password()));
    u.setRol("USER");
    repo.save(u);
    return Map.of("mensaje", "Usuario registrado");
  }

  @PostMapping("/login")
  public Map<String, String> login(@Valid @RequestBody AuthRequest r) {
    Authentication a = authManager.authenticate(
        new UsernamePasswordAuthenticationToken(r.username(), r.password()));
    Instant now = Instant.now();
    JwtClaimsSet claims = JwtClaimsSet.builder()
        .subject(a.getName())
        .issuedAt(now)
        .expiresAt(now.plus(1, ChronoUnit.HOURS))
        .build();
    String token = encoder.encode(
        JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS256).build(), claims)).getTokenValue();
    return Map.of("token", token);
  }
}