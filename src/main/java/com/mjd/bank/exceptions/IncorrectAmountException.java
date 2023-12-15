package com.mjd.bank.exceptions;

public class IncorrectAmountException extends RuntimeException {
    public IncorrectAmountException(String message) {
      super(message);
    }
}
