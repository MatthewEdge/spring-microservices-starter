package io.medgelabs.jwt;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class Jwt {
  private String userId;
  private String role;
}
