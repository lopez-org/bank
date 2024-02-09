package com.mjd.bank.exceptions;

public class IncorrectAccountTypeException extends RuntimeException{
    public IncorrectAccountTypeException(String message) {
        super(message);
    }
}
