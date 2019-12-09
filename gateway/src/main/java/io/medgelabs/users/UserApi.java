package io.medgelabs.users;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import feign.Param;

@FeignClient("users")
public interface UserApi {

  default boolean userExists(String userId) {
    return _userExists(userId).getStatusCodeValue() == HttpStatus.OK.value();
  }

  @GetMapping("/users/{userId}")
  ResponseEntity<?> _userExists(@Param("userId") String userId);
}
