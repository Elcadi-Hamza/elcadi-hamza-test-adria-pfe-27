package com.hamza.transactionservice.service;

import com.hamza.transactionservice.dtos.TransactionRequestDto;
import com.hamza.transactionservice.dtos.TransactionResponseDto;
import com.hamza.transactionservice.exceptions.AccountNotActif;
import com.hamza.transactionservice.exceptions.TransactionAmountNotRespected;
import com.hamza.transactionservice.exceptions.TransactionNotFound;

import java.util.List;
import java.util.UUID;

public interface TransactionService {
    List<TransactionResponseDto> getAllTransactions();
    TransactionResponseDto getTransactionById(UUID id) throws TransactionNotFound;
    TransactionResponseDto saveTransaction(TransactionRequestDto requestDto) throws AccountNotActif, TransactionAmountNotRespected;
}
