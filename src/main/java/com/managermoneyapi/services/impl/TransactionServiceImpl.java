package com.managermoneyapi.services.impl;

import com.managermoneyapi.dto.TransactionDto;
import com.managermoneyapi.entity.*;
import com.managermoneyapi.repositories.*;
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
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    TransactionDetailRepository transactionDetailRepository;
    @Autowired
    TransferRepository transferRepository;

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
                .amount(transactionDto.getAmount())
                .created_at(LocalDate.now().toString())
                .build();
        Transaction transactionSaved = transactionRepository.save(transaction);

       if(transactionDto.getType_transaction().name().equals("TRANSACTION")){
           System.out.println("TRANSACTION");
            Optional<Account> account = accountRepository.findById(transactionDto.getAccount_id());
            Optional<Category> category = categoryRepository.findById(transactionDto.getCategory_id());

            if(account.isPresent() && category.isPresent()){
                TransactionDetail detail = TransactionDetail
                        .builder()
                        .transaction(transactionSaved)
                        .account(account.get())
                        .category(category.get())
                        .amount(transactionDto.getAmount())
                        .build();

                transactionDetailRepository.save(detail);
            }
        }

        if(transactionDto.getType_transaction().name().equals("TRANSFER")) {
            System.out.println("TRANSFER");
            Optional<Account> accountOrigin = accountRepository.findById(transactionDto.getAccount_origin_id());
            Optional<Account> accountDestination = accountRepository.findById(transactionDto.getAccount_destination_id());

            if(accountOrigin.isPresent() && accountDestination.isPresent()){
                Transfer transfer = Transfer
                        .builder()
                        .accountOrigin(accountOrigin.get())
                        .accountDestination(accountDestination.get())
                        .transaction(transactionSaved)
                        .amount(transactionDto.getAmount())
                        .build();

                transferRepository.save(transfer);
            }
        }

        return transactionSaved;
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
