package com.test.evermeta.dto;
import java.time.Instant;
import java.util.Map;

public class ErrorResponse {
  public Instant timestamp = Instant.now();
  public int status;
  public String error;
  public String message;
  public Map<String, String> fieldErrors;

  public Instant getTimestamp() { return timestamp; }
  public int getStatus() { return status; }
  public String getError() { return error; }
  public String getMessage() { return message; }
  public Map<String, String> getFieldErrors() { return fieldErrors; }

  public void setStatus(int status) { this.status = status; }
  public void setError(String error) { this.error = error; }
  public void setMessage(String message) { this.message = message; }
  public void setFieldErrors(Map<String, String> fieldErrors) { this.fieldErrors = fieldErrors; }
}
