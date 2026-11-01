package com.hamza.accountservice.mapper;

import com.hamza.accountservice.dtos.AccountRequestDto;
import com.hamza.accountservice.dtos.AccountResponseDto;
import com.hamza.accountservice.domain.Account;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class AccountMapper {
    private ModelMapper modelMapper = new ModelMapper();
    public AccountResponseDto fromAccount(Account account) {
        return modelMapper.map(account,AccountResponseDto.class);
    }
    public Account fromAccountRequestDto(AccountRequestDto requestDTO) {
        return modelMapper.map(requestDTO, Account.class);
    }
}
