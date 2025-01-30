package com.cdleldt.auth.authCode;

public class AuthCodeNotFoundException extends RuntimeException {
  public AuthCodeNotFoundException(String message) {
    super(message);
  }
}
