package io.medgelabs.login;

import static org.springframework.http.ResponseEntity.notFound;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Value;

@RestController
@AllArgsConstructor
public class LoginController {

  @PostMapping("/login")
  public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
    return notFound().build();
  }

  @Value
  @AllArgsConstructor
  static class LoginRequest {
    @NotBlank private String username;
    @NotBlank private String password;
  }
}
