package com.mjd.bank.exceptions.factory;

import com.mjd.bank.exceptions.ApiException;
import org.springframework.http.HttpStatus;

public final class ExceptionFactory {

  private ExceptionFactory() {}

  public static ApiException resolve(Exception e) {
    return switch (e.getClass().getSimpleName()) {
      case "NotFoundException" -> build(e.getMessage(), HttpStatus.NOT_FOUND, e);
      case "ExpiredJwtException",
          "UnsupportedJwtException",
          "MalformedJwtException",
          "NotOwnerException",
          "SignatureException" -> build(e.getMessage(), HttpStatus.UNAUTHORIZED, e);
      case "IncorrectAmountException",
          "IncorrectAccountTypeException" -> build(e.getMessage(), HttpStatus.BAD_REQUEST, e);
      case "InvalidNameException" -> build(e.getMessage(), HttpStatus.CONFLICT, e);
      case "NoResourceFoundException" -> build(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, e);
      default -> build("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR, e);
    };
  }

  private static ApiException build(String message, HttpStatus status, Throwable cause) {
    return ApiException.builder()
        .message(message)
        .statusCode(status)
        .cause(cause)
        .build();
  }
}
