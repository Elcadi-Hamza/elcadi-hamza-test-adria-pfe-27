package com.hamza.transactionservice.feign;

import com.hamza.transactionservice.component.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.util.UUID;

@FeignClient(name = "account-service")
public interface AccountServiceClient {

    @GetMapping("/accounts")
    List<Account> getAllAccounts();

    @GetMapping("/accounts/{id}")
    Account getAccountById(@PathVariable UUID id);

    @PutMapping("/accounts/{id}/balance")
    Account updateBalance(
            @PathVariable UUID id,
            @RequestBody BigDecimal balance
    );
}