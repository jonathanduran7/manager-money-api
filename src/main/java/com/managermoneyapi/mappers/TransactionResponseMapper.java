package com.managermoneyapi.mappers;

import com.managermoneyapi.dto.TransactionResponseDto;
import com.managermoneyapi.entity.Transaction;
import org.modelmapper.ModelMapper;

public class TransactionResponseMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static TransactionResponseDto toDTO(Transaction transaction) {
        TransactionResponseDto dto = modelMapper.map(transaction, TransactionResponseDto.class);
        dto.setAccountName(transaction.getAccount().getName());
        dto.setCategoryName(transaction.getCategory().getName());
        return dto;
    }
}
