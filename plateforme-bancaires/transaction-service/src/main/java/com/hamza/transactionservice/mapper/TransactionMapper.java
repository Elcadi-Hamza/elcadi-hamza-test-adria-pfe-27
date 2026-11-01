package com.hamza.transactionservice.mapper;

import com.hamza.transactionservice.domain.Transaction;
import com.hamza.transactionservice.dtos.TransactionRequestDto;
import com.hamza.transactionservice.dtos.TransactionResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class TransactionMapper {
    private ModelMapper mapper = new ModelMapper();
    public TransactionMapper () {
        mapper.emptyTypeMap(Transaction.class, TransactionResponseDto.class)
                .addMappings(m-> m.map(Transaction::getAccountId, TransactionResponseDto::setAccountId))
                .implicitMappings();
    }
    public TransactionResponseDto fromTransaction(Transaction transaction) {
        return mapper.map(transaction, TransactionResponseDto.class);
    }

    public Transaction fromTransactionRequestDto (TransactionRequestDto transactionRequestDto) {
        return mapper.map(transactionRequestDto, Transaction.class);
    }
}
