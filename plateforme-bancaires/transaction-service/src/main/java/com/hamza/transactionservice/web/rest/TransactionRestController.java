package com.hamza.transactionservice.web.rest;

import com.hamza.transactionservice.dto.TransactionRequestDTO;
import com.hamza.transactionservice.dto.TransactionResponseDTO;
import com.hamza.transactionservice.service.TransactionService;
import lombok.AllArgsConstructor;
import org.modelmapper.spi.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class TransactionRestController {

    private TransactionService transactionService;

    @GetMapping("/transactions")
    public List<TransactionResponseDTO> listTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/transactions/{id}")
    public TransactionResponseDTO getTransactionById(@PathVariable UUID id) {
        return transactionService.getTransaction(id);
    }

    @GetMapping("/transactions/account/{accountId}")
    public List<TransactionResponseDTO> getTransactionsByAccount(
            @PathVariable UUID accountId) {
        return transactionService.getTransactionsByAccount(accountId);
    }

    @PostMapping("/transactions")
    public TransactionResponseDTO saveTransaction(
            @RequestBody TransactionRequestDTO request) {
        return transactionService.saveTransaction(request);
    }
}