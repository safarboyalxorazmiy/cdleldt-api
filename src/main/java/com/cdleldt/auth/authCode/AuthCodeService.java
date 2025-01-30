package com.cdleldt.auth.authCode;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthCodeService {
  private final AuthCodeRepository authCodeRepository;

  public String createCode(String email) {
    Random random = new Random();
    int code = 10000 + random.nextInt(90000);

    AuthCode authCode = new AuthCode();
    authCode.setEmail(email);
    authCode.setCode(String.valueOf(code));
    authCodeRepository.save(authCode);

    return String.valueOf(code);
  }

  public void checkCode(String email, String code) {
    List<AuthCode> codes = authCodeRepository.findByEmailAndCode(email, code);

    for (AuthCode authCode : codes) {
      if (authCode.getCode().equals(code)) {
        return;
      }
    }

    throw new AuthCodeNotFoundException("NO_CODE");
  }
}