package com.hamza.accountservice.web.graphQL;

import com.hamza.accountservice.dtos.AccountRequestDto;
import com.hamza.accountservice.dtos.AccountResponseDto;
import com.hamza.accountservice.exceptions.AccountNotFound;
import com.hamza.accountservice.service.AccountService;
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
public class AccountGraphQLController {
    private AccountService accountService;

    @QueryMapping
    public List<AccountResponseDto> accountList() {
        return accountService.listAccount();
    }

    @QueryMapping
    public AccountResponseDto getAccountById (@Argument UUID id) throws AccountNotFound {
        return accountService.getAccountById(id);
    }

    @MutationMapping
    public AccountResponseDto saveAccount (@Argument AccountRequestDto requestDto) {
        return accountService.saveAccount(requestDto);
    }

    @MutationMapping
    public AccountResponseDto updateAccount(@Argument AccountRequestDto requestDto) throws AccountNotFound {
        return accountService.updateAccount(requestDto);
    }

    @MutationMapping
    public void deleteAccount(@Argument UUID id) throws AccountNotFound{
        accountService.deleteAccount(id);
    }

}