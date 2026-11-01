package com.hamza.transactionservice.exceptions;

public class AccountNotActif extends RuntimeException {
    public AccountNotActif(String message) {
        super(message);
    }
}
