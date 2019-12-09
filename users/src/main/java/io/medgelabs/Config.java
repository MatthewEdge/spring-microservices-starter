package io.medgelabs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

// Centralized application config extraction
@Configuration
@Data
public class Config {

  @Value("${jwt.issuer}")
  private String jwtIssuer;

  @Value("${jwt.secret}")
  private String jwtSecret;
}
