package com.hamza.transactionservice.web.graphql;

import com.hamza.transactionservice.dto.TransactionRequestDTO;
import com.hamza.transactionservice.dto.TransactionResponseDTO;
import com.hamza.transactionservice.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class TransactionGraphQLController {

    private final TransactionService transactionService;

    @MutationMapping
    public TransactionResponseDTO saveTransaction(
            @Argument TransactionRequestDTO requestDTO) {

        return transactionService.saveTransaction(requestDTO);
    }

    @QueryMapping
    public TransactionResponseDTO getTransaction(@Argument UUID id) {
        return transactionService.getTransaction(id);
    }

    @QueryMapping
    public List<TransactionResponseDTO> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @QueryMapping
    public List<TransactionResponseDTO> getTransactionsByAccount(
            @Argument UUID accountId) {

        return transactionService.getTransactionsByAccount(accountId);
    }
}