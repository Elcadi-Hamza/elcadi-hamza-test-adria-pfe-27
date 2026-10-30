package com.hamza.transactionservice;

import com.hamza.transactionservice.component.Account;
import com.hamza.transactionservice.dto.TransactionRequestDTO;
import com.hamza.transactionservice.feign.AccountServiceClient;
import com.hamza.transactionservice.service.TransactionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableFeignClients
public class TransactionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransactionServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(
            TransactionService transactionService,
            AccountServiceClient accountServiceClient) {

        return args -> {

            List<Account> accounts = List.of(
                    accountServiceClient.getAccountById(
                            java.util.UUID.fromString("ACCOUNT_UUID_1")),
                    accountServiceClient.getAccountById(
                            java.util.UUID.fromString("ACCOUNT_UUID_2")),
                    accountServiceClient.getAccountById(
                            java.util.UUID.fromString("ACCOUNT_UUID_3"))
            );

            transactionService.saveTransaction(
                    TransactionRequestDTO.builder()
                            .accountId(accounts.get(0).getId())
                            .amount(new java.math.BigDecimal("150"))
                            .currency("MAD")
                            .merchantCategory("FOOD")
                            .country("MA")
                            .build()
            );

            transactionService.saveTransaction(
                    TransactionRequestDTO.builder()
                            .accountId(accounts.get(1).getId())
                            .amount(new java.math.BigDecimal("200"))
                            .currency("MAD")
                            .merchantCategory("SHOPPING")
                            .country("MA")
                            .build()
            );

            transactionService.saveTransaction(
                    TransactionRequestDTO.builder()
                            .accountId(accounts.get(2).getId())
                            .amount(new java.math.BigDecimal("300"))
                            .currency("MAD")
                            .merchantCategory("TRANSPORT")
                            .country("MA")
                            .build()
            );
        };
    }
}
