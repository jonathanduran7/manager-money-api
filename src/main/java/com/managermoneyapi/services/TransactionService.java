package com.managermoneyapi.services;

import com.managermoneyapi.dto.TransactionDto;
import com.managermoneyapi.dto.TransactionResponseDto;
import com.managermoneyapi.entity.Transaction;

import java.util.List;

public interface TransactionService extends BaseService<TransactionResponseDto, TransactionDto>{
    void delete(Long id);
}
