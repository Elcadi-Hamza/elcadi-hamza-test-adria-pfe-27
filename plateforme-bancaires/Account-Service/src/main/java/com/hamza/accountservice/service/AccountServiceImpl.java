package com.hamza.accountservice.service;

import com.hamza.accountservice.domain.AccountStatus;
import com.hamza.accountservice.dtos.AccountRequestDto;
import com.hamza.accountservice.dtos.AccountResponseDto;
import com.hamza.accountservice.exceptions.AccountNotFound;
import com.hamza.accountservice.mapper.AccountMapper;
import com.hamza.accountservice.domain.Account;
import com.hamza.accountservice.repository.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service @Transactional
@Slf4j @AllArgsConstructor
public class AccountServiceImpl implements AccountService{
    private AccountRepository accountRepository;
    private AccountMapper mapper;
    @Override
    public AccountResponseDto saveAccount(AccountRequestDto requestDTO) {
        Account account = mapper.fromAccountRequestDto(requestDTO);
        Account savedAccount = accountRepository.save(account);
        return mapper.fromAccount(savedAccount);
    }

    @Override
    public List<AccountResponseDto> listAccount() {
        return accountRepository.findAll().stream().map(mapper::fromAccount).collect(Collectors.toList());
    }

    @Override
    public AccountResponseDto getAccountById(UUID id) throws AccountNotFound {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFound("Account with id %s not found".formatted(id)));
//        Account account = accountRepository.findById(id).orElse(null);
//        if(account == null) throw new AccountNotFound(String.format("Account with id %s not found",id));

        return mapper.fromAccount(account);
    }

    @Override
    public AccountResponseDto updateAccount(AccountRequestDto requestDTO) throws AccountNotFound {
        Account account = accountRepository.findById(requestDTO.getId())
                .orElseThrow(() -> new AccountNotFound("Account with id %s not found".formatted(requestDTO.getId())));
//        Account account = accountRepository.findById(requestDTO.getId()).orElse(null);
//        if (account == null) throw new AccountNotFound(String.format("account %s not found",requestDTO.getId()));
        if(requestDTO.getCustomerReference() != null) account.setCustomerReference(requestDTO.getCustomerReference());
        if(requestDTO.getCurrency() != null) account.setCurrency(requestDTO.getCurrency());
        if(requestDTO.getStatus() != null) account.setStatus(requestDTO.getStatus());
        Account savedAccount = accountRepository.save(account);

        return mapper.fromAccount(savedAccount);
    }

    @Override
    public void deleteAccount(UUID id) throws AccountNotFound {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFound("Account with id %s not found".formatted(id)));
//        Account account = accountRepository.findById(id).orElse(null);
//        if(account == null) throw new AccountNotFound(String.format("Account with id %s not found",id));
        accountRepository.deleteById(id);
    }

    @Override
    public AccountStatus getAccountStatus(UUID id) throws AccountNotFound {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFound("Account with id %s not found".formatted(id)));
//        Account account = accountRepository.findById(id).orElse(null);
//        if(account == null) throw new AccountNotFound(String.format("Account with id %s not found",id));
        return account.getStatus();
    }

}

