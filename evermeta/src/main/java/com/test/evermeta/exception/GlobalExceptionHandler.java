package com.test.evermeta.exception;

import com.test.evermeta.dto.ErrorResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

  // Handles invalid @RequestBody (like SearchRequest with @Valid)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
    Map<String, String> fields = new HashMap<>();
    for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
      fields.put(fieldError.getField(), fieldError.getDefaultMessage());
    }

    ErrorResponse response = new ErrorResponse();
    response.setStatus(HttpStatus.BAD_REQUEST.value());
    response.setError("Bad Request");
    response.setMessage("Validation failed");
    response.setFieldErrors(fields);

    return ResponseEntity.badRequest().body(response);
  }

  // Handles invalid @PathVariable or @RequestParam (when using @Validated)
  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ErrorResponse> handleConstraintViolation(ConstraintViolationException ex) {
    Map<String, String> fields = new HashMap<>();
    for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
      String path = violation.getPropertyPath() == null ? "" : violation.getPropertyPath().toString();
      fields.put(path, violation.getMessage());
    }

    ErrorResponse response = new ErrorResponse();
    response.setStatus(HttpStatus.BAD_REQUEST.value());
    response.setError("Bad Request");
    response.setMessage("Validation failed");
    response.setFieldErrors(fields);

    return ResponseEntity.badRequest().body(response);
  }

  // Handles IllegalArgumentException you throw yourself
  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ErrorResponse> handleIllegalArgument(IllegalArgumentException ex) {
    ErrorResponse response = new ErrorResponse();
    response.setStatus(HttpStatus.BAD_REQUEST.value());
    response.setError("Bad Request");
    response.setMessage(ex.getMessage());

    return ResponseEntity.badRequest().body(response);
  }

}
