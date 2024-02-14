package com.mjd.bank.config;

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

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<SimpleMessageResponse> handleDepositExceptions(NotFoundException e) {
    return response(e.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(NotOwnerException.class)
  public ResponseEntity<SimpleMessageResponse> handleDepositExceptions(NotOwnerException e) {
    return response(e.getMessage(), HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(IncorrectAmountException.class)
  public ResponseEntity<SimpleMessageResponse> handleDepositExceptions(IncorrectAmountException e) {
    return response(e.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(IncorrectAccountTypeException.class)
  public ResponseEntity<SimpleMessageResponse> handleCreationExceptions(IncorrectAccountTypeException e) {
    return response(e.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(InvalidNameException.class)
  public ResponseEntity<SimpleMessageResponse> handleCreationExceptions(InvalidNameException e) {
    return response(e.getMessage(), HttpStatus.CONFLICT);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<SimpleMessageResponse> handleBasicException(Exception e) {
    return response(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }
  
  private ResponseEntity<SimpleMessageResponse> response(String message, HttpStatus status) {
    return new ResponseEntity<>(new SimpleMessageResponse(message), status);
  }

}
