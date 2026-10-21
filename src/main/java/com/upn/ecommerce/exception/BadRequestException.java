package com.upn.ecommerce.exception;

public class BadRequestException extends RuntimeException {
  public BadRequestException(String m) { super(m); }
}