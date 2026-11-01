package com.hamza.transactionservice.feign;

import com.hamza.transactionservice.model.Account;
import com.hamza.transactionservice.model.AccountStatus;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "account-service", url = "http://localhost:8081")
public interface AccountRestClient {
    @GetMapping("/accounts")
    @CircuitBreaker(name = "accountService", fallbackMethod = "getDefaultAccount")
    List<Account> listAccounts();

    @GetMapping("/accounts/{id}")
    @CircuitBreaker(name = "accountService", fallbackMethod = "getDefaultAccount")
    Account getAccountById(@PathVariable UUID id);

    @GetMapping("/accounts/{id}/status")
    @CircuitBreaker(name = "accountService", fallbackMethod = "getDefaultAccount")
    AccountStatus getAccountStatus(@PathVariable UUID id);

    default  Account getDefaultAccount(UUID id, Exception e)  {
        return new Account(id, "defaultAccountCustomerRefernce",AccountStatus.BLOCKED,"defaultAccountCurrency",0.0);
    }

}
