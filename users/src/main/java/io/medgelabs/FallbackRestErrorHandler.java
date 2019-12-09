 package io.medgelabs;

 import org.springframework.http.HttpHeaders;
 import org.springframework.http.HttpStatus;
 import org.springframework.http.ResponseEntity;
 import org.springframework.web.bind.MethodArgumentNotValidException;
 import org.springframework.web.bind.annotation.ControllerAdvice;
 import org.springframework.web.context.request.WebRequest;
 import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

 import lombok.AllArgsConstructor;
 import lombok.Data;
 import lombok.extern.slf4j.Slf4j;

 @ControllerAdvice
 @Slf4j
 public class FallbackRestErrorHandler extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {

    log.warn("Invalid request: {}", ex.getBindingResult().getAllErrors());
    return ResponseEntity.badRequest().body(new GenericError("input"));
  }

  @Data
  @AllArgsConstructor
  static class GenericError {
    private String error;
  }
 }
