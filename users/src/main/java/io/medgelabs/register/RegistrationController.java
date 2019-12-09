package io.medgelabs.register;

import static org.springframework.http.ResponseEntity.notFound;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@Slf4j
public class RegistrationController {

  @PostMapping("/register")
  public ResponseEntity<?> register(@Valid @RequestBody RegistrationRequest request) {
    return notFound().build();
  }

  @Value
  @AllArgsConstructor
  static class RegistrationRequest {
    @NotBlank
    @Size(min = 4, max = 64)
    @Pattern(regexp = "^[a-zA-Z0-9]{4,64}$")
    private String username;

    @NotBlank
    @Size(min = 8, max = 128)
    @Pattern(regexp = "^[a-zA-Z0-9\\?!@#\\$]{8,128}$")
    private String password;
  }
}
