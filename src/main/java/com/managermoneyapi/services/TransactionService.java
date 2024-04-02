package com.managermoneyapi.services;

import com.managermoneyapi.dto.TransactionDto;
import com.managermoneyapi.dto.TransactionResponseDto;
import com.managermoneyapi.entity.Transaction;

import java.util.List;

public interface TransactionService extends BaseService<Transaction, TransactionDto>{

    List<TransactionResponseDto> findAllMapper();

    void delete(Long id);
}
