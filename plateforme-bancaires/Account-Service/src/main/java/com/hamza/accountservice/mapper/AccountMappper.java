package com.hamza.accountservice.mapper;

import com.hamza.accountservice.dto.AccountRequestDTO;
import com.hamza.accountservice.dto.AccountResponseDTO;
import com.hamza.accountservice.domain.Account;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class AccountMappper {
    private ModelMapper modelMapper = new ModelMapper();
    public AccountResponseDTO fromAccount(Account account) {
        return modelMapper.map(account,AccountResponseDTO.class);
    }
    public Account fromAccountRequestDto(AccountRequestDTO requestDTO) {
        return modelMapper.map(requestDTO, Account.class);
    }
}
