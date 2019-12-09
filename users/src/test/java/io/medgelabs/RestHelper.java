package io.medgelabs;

import com.fasterxml.jackson.databind.ObjectMapper;

public interface RestHelper {

  // Serialize given object as Json with Jackson
  default String asJson(Object input) {
    try {
      return new ObjectMapper().writeValueAsString(input);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
