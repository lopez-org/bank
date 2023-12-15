package com.mjd.bank.exceptions;

public class NotOwnerException extends RuntimeException {

  public NotOwnerException(String message) {
    super(message);
  }
}
