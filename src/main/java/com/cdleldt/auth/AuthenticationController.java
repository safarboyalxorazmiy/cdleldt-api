package com.cdleldt.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;

  @PostMapping("/authenticate")
  public ResponseEntity<String> register(
    @RequestParam String email
  ) {
    try {
      service.sendVerificationCode(email);
      return ResponseEntity.ok("Verification code sent successfully.");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body("Failed to send verification code.");
    }
  }

  @PostMapping("/authenticate/google")
  public ResponseEntity<AuthenticationResponse> registerWithGoogle(
    @RequestParam String token
  ) {
    return ResponseEntity.ok(service.registerWithGoogle(token));
  }

  @PostMapping("/authenticate/verify")
  public ResponseEntity<AuthenticationResponse> register(
    @RequestBody RegisterRequest request
  ) {
    return ResponseEntity.ok(service.register(request));
  }

}
