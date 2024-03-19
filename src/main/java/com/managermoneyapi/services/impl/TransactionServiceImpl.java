package com.managermoneyapi.services.impl;

import com.managermoneyapi.dto.TransactionDto;
import com.managermoneyapi.entity.Transaction;
import com.managermoneyapi.repositories.TransactionRepository;
import com.managermoneyapi.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    @Override
    public Optional<Transaction> findById(Long id) {
        return transactionRepository.findById(id);
    }

    @Override
    public Transaction save(TransactionDto transactionDto) {
        Transaction transaction = Transaction
                .builder()
                .title(transactionDto.getTitle())
                .description(transactionDto.getDescription())
                .type_transaction(transactionDto.getType_transaction().name())
                .created_at(LocalDate.now().toString())
                .build();
        return transactionRepository.save(transaction);
    }

    @Override
    public Optional<Transaction> update(Long id, TransactionDto transactionDto) {

        Optional<Transaction> transactionOptional = transactionRepository.findById(id);

        if (transactionOptional.isPresent()){
            Transaction transactionToUpdate = transactionOptional.get();
            transactionToUpdate.setTitle(transactionDto.getTitle());
            transactionToUpdate.setDescription(transactionDto.getDescription());
            transactionToUpdate.setType_transaction(transactionDto.getType_transaction().name());
            return Optional.of(transactionRepository.save(transactionToUpdate));
        }

        return Optional.empty();
    }

    @Override
    public void delete(Long id) {
        Optional<Transaction> transactionOptional = transactionRepository.findById(id);

        if (transactionOptional.isPresent()){
            transactionRepository.delete(transactionOptional.get());
        }
    }
}
