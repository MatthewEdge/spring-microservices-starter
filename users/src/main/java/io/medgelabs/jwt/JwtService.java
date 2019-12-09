package io.medgelabs.jwt;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import io.medgelabs.Config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// Note: Make sure claims are mirrored between the create() and the verify() method

@Service
@AllArgsConstructor
@Slf4j
public class JwtService {

  private Config config;

  // Create a signed JWT for the given Username
  public Optional<String> create(Jwt body) {
    try {
      var jwt =
          JWT.create()
              .withIssuer(config.getJwtIssuer())
              .withExpiresAt(Date.valueOf(LocalDate.now().plusDays(1))) // 24h expiration
              .withClaim("id", body.getUserId())
              .withClaim("role", body.getRole())
              .sign(algorithm());

      return Optional.of(jwt);
    } catch (JWTCreationException err) {
      log.error("Failed to create new JWT", err);
      return Optional.empty();
    }
  }

  // Attempt to verify the given JWT is a valid signed JWT
  public Optional<Jwt> verify(String token) {
    try {
      var verifier =
          JWT.require(algorithm())
              .withIssuer(config.getJwtIssuer())
              .build(); // Reusable verifier instance

      var decoded = verifier.verify(token.replace("Bearer ", ""));
      var jwt =
          Jwt.builder()
              .userId(decoded.getClaim("id").asString())
              .role(decoded.getClaim("role").asString())
              .build();

      return Optional.of(jwt);
    } catch (JWTVerificationException err) {
      log.error("Invalid JWT received: " + err.getMessage());
      return Optional.empty();
    }
  }

  private Algorithm algorithm() {
    return Algorithm.HMAC256(config.getJwtSecret());
  }
}
