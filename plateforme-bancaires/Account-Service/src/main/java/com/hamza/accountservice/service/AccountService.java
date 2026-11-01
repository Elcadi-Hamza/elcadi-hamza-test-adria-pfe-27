package com.hamza.accountservice.service;

import com.hamza.accountservice.domain.AccountStatus;
import com.hamza.accountservice.dtos.AccountRequestDto;
import com.hamza.accountservice.dtos.AccountResponseDto;
import com.hamza.accountservice.exceptions.AccountNotFound;

import java.util.List;
import java.util.UUID;

public interface AccountService {
    AccountResponseDto saveAccount(AccountRequestDto requestDTO);
    List<AccountResponseDto> listAccount();
    AccountResponseDto getAccountById(UUID id) throws AccountNotFound;
    AccountResponseDto updateAccount(AccountRequestDto requestDTO) throws AccountNotFound;
    void deleteAccount(UUID id) throws AccountNotFound;
    AccountStatus getAccountStatus(UUID id) throws AccountNotFound;
}
