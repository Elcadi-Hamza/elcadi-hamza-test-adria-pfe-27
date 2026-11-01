package com.hamza.transactionservice.web.graphQL;

import com.hamza.transactionservice.dtos.TransactionRequestDto;
import com.hamza.transactionservice.dtos.TransactionResponseDto;
import com.hamza.transactionservice.exceptions.AccountNotActif;
import com.hamza.transactionservice.exceptions.TransactionAmountNotRespected;
import com.hamza.transactionservice.exceptions.TransactionNotFound;
import com.hamza.transactionservice.service.TransactionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
@Slf4j @AllArgsConstructor
public class TransactionGrapQLController {
    TransactionService service;

    @QueryMapping
    public List<TransactionResponseDto> getAllTransactions() {
        return service.getAllTransactions();
    }

    @QueryMapping
    public TransactionResponseDto getTransactionById(@Argument UUID id) throws TransactionNotFound {
        return service.getTransactionById(id);
    }

    @MutationMapping
    public TransactionResponseDto saveTransaction(@Argument TransactionRequestDto requestDto) throws AccountNotActif, TransactionAmountNotRespected {
        return service.saveTransaction(requestDto);
    }

}