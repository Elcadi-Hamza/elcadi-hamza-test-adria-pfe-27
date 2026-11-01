package com.hamza.transactionservice.service;

import com.hamza.transactionservice.domain.Transaction;
import com.hamza.transactionservice.dtos.TransactionRequestDto;
import com.hamza.transactionservice.dtos.TransactionResponseDto;
import com.hamza.transactionservice.exceptions.AccountNotActif;
import com.hamza.transactionservice.exceptions.TransactionAmountNotRespected;
import com.hamza.transactionservice.exceptions.TransactionNotFound;
import com.hamza.transactionservice.feign.AccountRestClient;
import com.hamza.transactionservice.mapper.TransactionMapper;
import com.hamza.transactionservice.model.Account;
import com.hamza.transactionservice.model.AccountStatus;
import com.hamza.transactionservice.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service @Transactional
@Slf4j @AllArgsConstructor
public class TransactionServiceImp implements TransactionService{
    private TransactionRepository transactionRepository;
    private AccountRestClient accountRestClient;
    private TransactionMapper mapper;

    private TransactionResponseDto toResponse(Transaction transaction) {
        TransactionResponseDto responseDto = mapper.fromTransaction(transaction);
        Account account = accountRestClient.getAccountById(transaction.getAccountId());
        responseDto.setAccount(account);
        return  responseDto;
    }
    @Override
    public List<TransactionResponseDto> getAllTransactions() {
        return transactionRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    public TransactionResponseDto getTransactionById(UUID id) throws TransactionNotFound {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new TransactionNotFound("Transaction not found"));

        return toResponse(transaction);
    }

    @Override
    public TransactionResponseDto saveTransaction(TransactionRequestDto requestDto) throws AccountNotActif, TransactionAmountNotRespected {

        if(requestDto.getAccountId() == null || requestDto.getAmount().signum() <= 0) {
            throw new TransactionAmountNotRespected("Amount <= 0");
        }

        Account account = accountRestClient.getAccountById(requestDto.getAccountId());
        if(account.getStatus() != AccountStatus.ACTIVE) {
            throw new AccountNotActif(String.format("Account %s not active",account.getId()));
        }

        Transaction transaction = Transaction.builder()
                .accountId(account.getId())
                        .amount(requestDto.getAmount())
                                .currency(requestDto.getCurrency())
                                        .merchantCategory(requestDto.getMerchantCategory())
                                                .country(requestDto.getCountry())
                                                        .createdAt(Instant.now())
                                                                .build();


        Transaction savedTransaction = transactionRepository.save(transaction);
        savedTransaction.setAccount(account);
        return mapper.fromTransaction(savedTransaction);
    }
}