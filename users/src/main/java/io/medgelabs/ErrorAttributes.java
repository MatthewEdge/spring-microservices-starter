package io.medgelabs;

import java.util.Map;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import lombok.extern.slf4j.Slf4j;

// Override the default Spring Boot error attributes sent in uncaught errors

@Component
@Slf4j
public class ErrorAttributes extends DefaultErrorAttributes {

  @Override
  public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
    Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, false);

    errorAttributes.forEach((k, v) -> log.info("{}: {}", k, v));
    errorAttributes.remove("errors");
    errorAttributes.remove("message");
    errorAttributes.remove("path");
    errorAttributes.remove("timestamp");

    return errorAttributes;
  }
}
