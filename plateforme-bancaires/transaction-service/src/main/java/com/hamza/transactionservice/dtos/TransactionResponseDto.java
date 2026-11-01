package com.hamza.transactionservice.dtos;

import com.hamza.transactionservice.model.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data @NoArgsConstructor
@AllArgsConstructor @Builder
public class TransactionResponseDto {
    private UUID id;
    private UUID accountId;
    private BigDecimal amount;
    private String currency;
    private String merchantCategory;
    private String country;
    private Instant createdAt;
    private Account account;
}
