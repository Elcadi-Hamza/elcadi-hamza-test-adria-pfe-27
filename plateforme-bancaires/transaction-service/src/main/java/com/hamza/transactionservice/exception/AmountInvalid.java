package com.hamza.transactionservice.exception;

public class AmountInvalid extends RuntimeException {
    public AmountInvalid(String message) {
        super(message);
    }
}
