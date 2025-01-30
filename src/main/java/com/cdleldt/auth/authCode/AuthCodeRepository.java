package com.cdleldt.auth.authCode;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthCodeRepository extends JpaRepository<AuthCode, Long> {
  List<AuthCode> findByEmailAndCode(String email, String code);
}