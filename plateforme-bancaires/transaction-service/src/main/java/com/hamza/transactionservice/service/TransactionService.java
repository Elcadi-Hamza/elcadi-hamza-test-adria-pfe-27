package com.hamza.transactionservice.service;

import com.hamza.transactionservice.dto.TransactionRequestDTO;
import com.hamza.transactionservice.dto.TransactionResponseDTO;
import com.hamza.transactionservice.exception.AccountNotFoundException;
import com.hamza.transactionservice.exception.AmountInvalid;

import java.util.List;
import java.util.UUID;

public interface TransactionService {
    TransactionResponseDTO saveTransaction(TransactionRequestDTO requestDTO) throws AccountNotFoundException, AmountInvalid;
    TransactionResponseDTO getTransaction(UUID id) throws AccountNotFoundException;
    List<TransactionResponseDTO> getAllTransactions();
    List<TransactionResponseDTO> getTransactionsByAccount(UUID accountId);
}
