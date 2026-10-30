package com.hamza.accountservice.dto;

import com.hamza.accountservice.domain.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor @AllArgsConstructor @Builder
public class AccountRequestDTO {
    private UUID id;
    private String customerReference;
    private AccountStatus status;
    private String currency;
    private double balance;
}
