package com.mjd.bank.config;

import static com.mjd.bank.exceptions.factory.ExceptionFactory.resolve;

import com.mjd.bank.dtos.response.SimpleMessageResponse;
import com.mjd.bank.exceptions.*;
import io.jsonwebtoken.ExpiredJwtException;
import javax.security.auth.login.CredentialExpiredException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<SimpleMessageResponse> handleBasicException(Exception e) {
    return response(resolve(e));
  }

  private ResponseEntity<SimpleMessageResponse> response(ApiException e) {
    return response(e.getMessage(), e.getStatusCode());
  }

  private ResponseEntity<SimpleMessageResponse> response(String message, HttpStatus status) {
    return new ResponseEntity<>(new SimpleMessageResponse(message), status);
  }

}
