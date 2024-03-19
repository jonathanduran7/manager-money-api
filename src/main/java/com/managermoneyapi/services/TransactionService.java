package com.managermoneyapi.services;

import com.managermoneyapi.dto.TransactionDto;
import com.managermoneyapi.entity.Transaction;

public interface TransactionService extends BaseService<Transaction, TransactionDto>{
    void delete(Long id);
}
