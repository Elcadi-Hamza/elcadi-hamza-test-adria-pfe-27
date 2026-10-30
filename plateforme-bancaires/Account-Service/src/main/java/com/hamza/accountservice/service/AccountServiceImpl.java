package com.hamza.accountservice.service;

import com.hamza.accountservice.dto.AccountRequestDTO;
import com.hamza.accountservice.dto.AccountResponseDTO;
import com.hamza.accountservice.exception.AccountNotFound;
import com.hamza.accountservice.mapper.AccountMappper;
import com.hamza.accountservice.domain.Account;
import com.hamza.accountservice.repository.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service @Transactional
@Slf4j @AllArgsConstructor
public class AccountServiceImpl implements AccountSerivce{
    private AccountRepository accountRepository;
    private AccountMappper mapper;
    @Override
    public AccountResponseDTO saveAccount(AccountRequestDTO requestDTO) {
        Account account = mapper.fromAccountRequestDto(requestDTO);
        Account savedAccount = accountRepository.save(account);
        return mapper.fromAccount(account);
    }

    @Override
    public List<AccountResponseDTO> listAccount() {
        return accountRepository.findAll().stream().map(mapper::fromAccount).collect(Collectors.toList());
    }

    @Override
    public AccountResponseDTO getAccountById(UUID id) throws AccountNotFound {
        Account account = accountRepository.findById(id).orElse(null);
        if(account == null) throw new AccountNotFound(String.format("Account with id %s not found",id));

        return mapper.fromAccount(account);
    }

    @Override
    public AccountResponseDTO updateAccount(AccountRequestDTO requestDTO) throws AccountNotFound {
        Account account = accountRepository.findById(requestDTO.getId()).orElse(null);
        if (account == null) throw new AccountNotFound(String.format("account %s not found",requestDTO.getId()));
        Account savedAccount = accountRepository.save(account);

        return mapper.fromAccount(savedAccount);
    }

    @Override
    public void deleteAccount(UUID id) throws AccountNotFound {
        Account account = accountRepository.findById(id).orElse(null);
        if(account == null) throw new AccountNotFound(String.format("Account with id %s not found",id));
        accountRepository.deleteById(id);
    }
//    @Override
//    public AccountResponseDTO updateBalance(UUID id, double balance) throws AccountNotFound {
//        Account account = accountRepository.findById(id).orElse(null);
//
//        if (account == null)
//            throw new AccountNotFound(String.format("Account with id %s not found", id));
//
//        account.setBalance(balance);
//
//        Account savedAccount = accountRepository.save(account);
//
//        return mapper.fromAccount(savedAccount);
//    }

}
