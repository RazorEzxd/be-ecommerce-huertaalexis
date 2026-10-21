package com.upn.ecommerce.exception;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
  private ResponseEntity<Map<String, Object>> build(HttpStatus s, Object msg) {
    return ResponseEntity.status(s).body(Map.of("status", s.value(),
        "error", s.getReasonPhrase(), "mensaje", msg));
  }
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<Map<String, Object>> notFound(ResourceNotFoundException e) {
    return build(HttpStatus.NOT_FOUND, e.getMessage());
  }
  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<Map<String, Object>> bad(BadRequestException e) {
    return build(HttpStatus.BAD_REQUEST, e.getMessage());
  }
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, Object>> valid(MethodArgumentNotValidException e) {
    Map<String, String> errs = new HashMap<>();
    e.getBindingResult().getFieldErrors().forEach(f -> errs.put(f.getField(), f.getDefaultMessage()));
    return build(HttpStatus.BAD_REQUEST, errs);
  }
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<Map<String, Object>> json(HttpMessageNotReadableException e) {
    return build(HttpStatus.BAD_REQUEST, "JSON inválido");
  }
}