package com.hamza.transactionservice.service;

import com.hamza.transactionservice.component.Account;
import com.hamza.transactionservice.domain.Transaction;
import com.hamza.transactionservice.dto.TransactionRequestDTO;
import com.hamza.transactionservice.dto.TransactionResponseDTO;
import com.hamza.transactionservice.exception.AccountNotFoundException;
import com.hamza.transactionservice.exception.AmountInvalid;
import com.hamza.transactionservice.feign.AccountServiceClient;
import com.hamza.transactionservice.mapper.TransactionMapper;
import com.hamza.transactionservice.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final AccountServiceClient accountServiceClient;

    @Override
    public TransactionResponseDTO saveTransaction(TransactionRequestDTO requestDTO)
            throws AccountNotFoundException, AmountInvalid {

        if (requestDTO.getAmount() == null ||
                requestDTO.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new AmountInvalid("Amount must be greater than zero");
        }

        Account account = accountServiceClient.getAccountById(requestDTO.getAccountId());

        if (account == null) {
            throw new AccountNotFoundException("Account not found");
        }

        if (!"ACTIVE".equalsIgnoreCase(account.getStatus())) {
            throw new IllegalStateException("Account is not active");
        }

        if (!account.getCurrency().equalsIgnoreCase(requestDTO.getCurrency())) {
            throw new IllegalArgumentException("Currency mismatch");
        }

        if (account.getBalance().compareTo(requestDTO.getAmount()) < 0) {
            throw new IllegalArgumentException("Insufficient balance");
        }

        Transaction transaction =
                transactionMapper.fromTransactionRequestDTO(requestDTO);

        transaction.setId(null);
        transaction.setCreatedAt(Instant.now());

        Transaction savedTransaction = transactionRepository.save(transaction);

        BigDecimal newBalance =
                account.getBalance().subtract(requestDTO.getAmount());

        accountServiceClient.updateBalance(account.getId(), newBalance);

        return transactionMapper.fromTransaction(savedTransaction);
    }

    @Override
    @Transactional
    public TransactionResponseDTO getTransaction(UUID id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() ->
                        new AccountNotFoundException("Transaction not found"));

        return transactionMapper.fromTransaction(transaction);
    }

    @Override
    @Transactional
    public List<TransactionResponseDTO> getAllTransactions() {
        return transactionRepository.findAll()
                .stream()
                .map(transactionMapper::fromTransaction)
                .toList();
    }

    @Override
    @Transactional
    public List<TransactionResponseDTO> getTransactionsByAccount(UUID accountId) {
        return transactionRepository.findByAccountId(accountId)
                .stream()
                .map(transactionMapper::fromTransaction)
                .toList();
    }
}