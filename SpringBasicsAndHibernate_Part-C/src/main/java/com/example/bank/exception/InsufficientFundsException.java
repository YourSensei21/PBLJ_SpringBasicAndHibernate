package com.example.bank.exception;

// By extending RuntimeException, we tell Spring to automatically
// roll back the transaction if this exception is thrown.
public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(String message) {
        super(message);
    }
}