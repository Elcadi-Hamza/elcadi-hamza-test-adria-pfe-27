package com.hamza.transactionservice.web.rest;

import com.hamza.transactionservice.dtos.TransactionRequestDto;
import com.hamza.transactionservice.dtos.TransactionResponseDto;
import com.hamza.transactionservice.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class TransactionRestController {
    private TransactionService service;

    @GetMapping("/transactions")
    public List<TransactionResponseDto> getAllTransactions() {
        return service.getAllTransactions();
    }

    @GetMapping("/transactions/{id}")
    public TransactionResponseDto getTransactionById(@PathVariable UUID id) {
        return service.getTransactionById(id);
    }

    @PostMapping("/transaction")
    public TransactionResponseDto saveTransaction(@RequestBody TransactionRequestDto requestDto) {
        return service.saveTransaction(requestDto);
    }

}
