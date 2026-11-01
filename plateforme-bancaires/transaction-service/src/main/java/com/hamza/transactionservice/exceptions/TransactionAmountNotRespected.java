package com.hamza.transactionservice.exceptions;

public class TransactionAmountNotRespected extends RuntimeException {
    public TransactionAmountNotRespected(String message) {
        super(message);
    }
}
