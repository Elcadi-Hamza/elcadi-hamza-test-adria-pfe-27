package com.hamza.transactionservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor @Builder
public class TransactionRequestDTO {
    private UUID accountId;
    private UUID destinationAccountId;
    private BigDecimal amount;
    private String currency;
    private String merchantCategory;
    private String country;
}