package com.managermoneyapi.services.impl;

import com.managermoneyapi.dto.TransactionDto;
import com.managermoneyapi.entity.*;
import com.managermoneyapi.repositories.*;
import com.managermoneyapi.services.TransactionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
    AccountBalanceRepository accountBalanceRepository;

    @Override
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    @Override
    public Optional<Transaction> findById(Long id) {
        return transactionRepository.findById(id);
    }

    @Override
    @Transactional
    public Transaction save(TransactionDto transactionDto) {

        Optional<Account> account = accountRepository.findById(transactionDto.getAccount_id());
        Optional<Category> category = categoryRepository.findById(transactionDto.getCategory_id());

        if (account.isPresent() || category.isPresent()){
            Transaction transaction = Transaction
                    .builder()
                    .title(transactionDto.getTitle())
                    .description(transactionDto.getDescription())
                    .amount(transactionDto.getAmount())
                    .date_time(LocalDate.now().toString())
                    .category(category.get())
                    .account(account.get())
                    .build();

            Transaction transactionSaved = transactionRepository.save(transaction);

            Optional<AccountBalance> accountBalance = accountBalanceRepository.findByAccountId(transactionDto.getAccount_id());

            if(accountBalance.isPresent()){

                BigDecimal amount = transactionDto.getAmount();
                BigDecimal currentBalance = accountBalance.get().getBalance();

                BigDecimal newBalance = currentBalance.add(amount);

                if (newBalance.compareTo(BigDecimal.ZERO) >= 0) {
                    accountBalance.get().setBalance(newBalance);
                    accountBalanceRepository.save(accountBalance.get());
                } else {
                    throw new IllegalStateException("Saldo insuficiente para realizar la transacción");
                }
            }
            return transactionSaved;
        }
        return null;
    }

    @Override
    public Optional<Transaction> update(Long id, TransactionDto transactionDto) {

//        Optional<Transaction> transactionOptional = transactionRepository.findById(id);

//        if (transactionOptional.isPresent()){
//            Transaction transactionToUpdate = transactionOptional.get();
//            transactionToUpdate.setTitle(transactionDto.getTitle());
//            transactionToUpdate.setDescription(transactionDto.getDescription());
//            transactionToUpdate.setType_transaction(transactionDto.getType_transaction().name());
//            return Optional.of(transactionRepository.save(transactionToUpdate));
//        }

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
