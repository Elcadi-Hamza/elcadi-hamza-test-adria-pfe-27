package com.hamza.accountservice.web.graphql;

import com.hamza.accountservice.domain.Account;
import com.hamza.accountservice.dto.AccountRequestDTO;
import com.hamza.accountservice.dto.AccountResponseDTO;
import com.hamza.accountservice.service.AccountSerivce;
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
public class AccountGraphQlController {
    private AccountSerivce accountService;

    @QueryMapping
    public AccountResponseDTO account(@Argument UUID id) {
        return accountService.getAccountById(id);
    }

    @QueryMapping
    public List<AccountResponseDTO> accounts() {
        return accountService.listAccount();
    }

    @MutationMapping
    public AccountResponseDTO createAccount(@Argument AccountInput input) {

        AccountRequestDTO account = new AccountRequestDTO();
        account.setCustomerReference(input.customerReference());
        account.setCurrency(input.currency());

        return accountService.saveAccount(account);
    }
}
