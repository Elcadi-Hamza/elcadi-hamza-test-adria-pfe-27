package com.hamza.accountservice.service;

import com.hamza.accountservice.dto.AccountRequestDTO;
import com.hamza.accountservice.dto.AccountResponseDTO;
import com.hamza.accountservice.exception.AccountNotFound;

import java.util.List;
import java.util.UUID;

public interface AccountSerivce {
    AccountResponseDTO saveAccount(AccountRequestDTO requestDTO);
    List<AccountResponseDTO> listAccount();
    AccountResponseDTO getAccountById(UUID id) throws AccountNotFound;
    AccountResponseDTO updateAccount(AccountRequestDTO requestDTO) throws AccountNotFound;
    void deleteAccount(UUID id) throws AccountNotFound;
    AccountResponseDTO updateBalance(UUID id, double balance) throws AccountNotFound;
}
