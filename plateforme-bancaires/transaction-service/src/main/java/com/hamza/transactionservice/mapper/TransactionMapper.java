package com.hamza.transactionservice.mapper;

import com.hamza.transactionservice.domain.Transaction;
import com.hamza.transactionservice.dto.TransactionRequestDTO;
import com.hamza.transactionservice.dto.TransactionResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class TransactionMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    public TransactionResponseDTO fromTransaction(Transaction transaction) {
        return modelMapper.map(
                transaction,
                TransactionResponseDTO.class
        );
    }

    public Transaction fromTransactionRequestDTO(
            TransactionRequestDTO requestDTO) {

        return modelMapper.map(
                requestDTO,
                Transaction.class
        );
    }
}