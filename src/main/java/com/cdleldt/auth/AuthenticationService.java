package com.cdleldt.auth;

import com.cdleldt.auth.authCode.AuthCodeService;
import com.cdleldt.config.JwtService;
import com.cdleldt.email.EmailService;
import com.cdleldt.token.Token;
import com.cdleldt.token.TokenRepository;
import com.cdleldt.token.TokenType;
import com.cdleldt.user.Role;
import com.cdleldt.user.User;
import com.cdleldt.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserRepository repository;
  private final TokenRepository tokenRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthCodeService authCodeService;
  private final EmailService emailService;

  public AuthenticationResponse register(RegisterRequest request) {
    authCodeService.checkCode(request.getEmail(), request.getCode());

    var user = User.builder()
      .email(request.getEmail())
      .password(passwordEncoder.encode(request.getEmail()))
      .role(Role.USER)
      .build();
    var savedUser = repository.save(user);
    var jwtToken = jwtService.generateToken(user);
    saveUserToken(savedUser, jwtToken);
    return AuthenticationResponse.builder()
      .accessToken(jwtToken)
      .build();
  }

  private void saveUserToken(User user, String jwtToken) {
    var token = Token.builder()
      .user(user)
      .token(jwtToken)
      .tokenType(TokenType.BEARER)
      .expired(false)
      .revoked(false)
      .build();
    tokenRepository.save(token);
  }

  @Async
  public void sendVerificationCode(String email) {
    String code = authCodeService.createCode(email);
    emailService.sendStyledRegistrationCode(
      email,
      code
    );
  }

  public AuthenticationResponse registerWithGoogle(String token) {
    String email = fetchUserEmail(token);

    var user = User.builder()
      .email(email)
      .password(passwordEncoder.encode(email))
      .role(Role.USER)
      .build();
    var savedUser = repository.save(user);
    var jwtToken = jwtService.generateToken(user);
    saveUserToken(savedUser, jwtToken);
    return AuthenticationResponse.builder()
      .accessToken(jwtToken)
      .build();
  }

  public static String fetchUserEmail(String token) {
    StringBuilder response = new StringBuilder();

    try {
      URL url = new URL("https://www.googleapis.com/oauth2/v1/userinfo");
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();

      connection.setRequestMethod("GET");
      connection.setRequestProperty("Authorization", "Bearer " + token);

      int responseCode = connection.getResponseCode();
      System.out.println("Response Code: " + responseCode);

      if (responseCode == HttpURLConnection.HTTP_OK) {
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
          response.append(inputLine);
        }
        in.close();

        JsonObject jsonObject = JsonParser.parseString(response.toString()).getAsJsonObject();
        if (jsonObject.has("email")) {
          return jsonObject.get("email").getAsString();
        }
      } else {
        BufferedReader errorReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        String errorLine;
        while ((errorLine = errorReader.readLine()) != null) {
          response.append(errorLine);
        }
        errorReader.close();
        System.out.println("Error Response: " + response.toString());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return null;
  }
}