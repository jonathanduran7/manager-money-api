package com.managermoneyapi.services.impl;

import com.managermoneyapi.dto.TransactionDto;
import com.managermoneyapi.dto.TransactionResponseDto;
import com.managermoneyapi.entity.*;
import com.managermoneyapi.mappers.TransactionResponseMapper;
import com.managermoneyapi.repositories.*;
import com.managermoneyapi.services.TransactionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<TransactionResponseDto> findAll() {
        return transactionRepository.findAll()
                .stream()
                .map(TransactionResponseMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TransactionResponseDto> findById(Long id) {

        return transactionRepository.findById(id)
                .map(TransactionResponseMapper::toDTO);
    }

    @Override
    @Transactional
    public TransactionResponseDto save(TransactionDto transactionDto) {

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
            return TransactionResponseMapper.toDTO(transactionSaved);
        }
        return null;
    }

    @Override
    @Transactional
    public Optional<TransactionResponseDto> update(Long id, TransactionDto transactionDto) {
        Optional<Transaction> transactionOptional = transactionRepository.findById(id);

        if (transactionOptional.isPresent()){
            Transaction transactionToUpdate = transactionOptional.get();

            Optional<AccountBalance> accountBalance = accountBalanceRepository.findByAccountId(transactionToUpdate.getAccount().getId());

            if(accountBalance.isPresent()) {
                BigDecimal currentBalance = accountBalance.get().getBalance();
                BigDecimal newBalance = currentBalance.subtract(transactionToUpdate.getAmount());
                BigDecimal newBalance2 = newBalance.add(transactionDto.getAmount());

                if (newBalance2.compareTo(BigDecimal.ZERO) >= 0) {
                    accountBalance.get().setBalance(newBalance2);
                    accountBalanceRepository.save(accountBalance.get());
                } else {
                    throw new IllegalStateException("Saldo insuficiente para realizar la transacción");
                }
            }

            transactionToUpdate.setTitle(transactionDto.getTitle());
            transactionToUpdate.setDescription(transactionDto.getDescription());
            transactionToUpdate.setAmount(transactionDto.getAmount());
            transactionToUpdate.setCategory(categoryRepository.findById(transactionDto.getCategory_id()).get());

            return Optional.of(TransactionResponseMapper.toDTO(
                    transactionRepository.save(transactionToUpdate)
            ));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void delete(Long id) {
        Optional<Transaction> transactionOptional = transactionRepository.findById(id);

        if (transactionOptional.isPresent()){
            Transaction transactionToDelete = transactionOptional.get();

            Optional<AccountBalance> accountBalance = accountBalanceRepository.findByAccountId(transactionToDelete.getAccount().getId());

            if(accountBalance.isPresent()) {
                BigDecimal currentBalance = accountBalance.get().getBalance();
                BigDecimal newBalance = currentBalance.subtract(transactionToDelete.getAmount());
                accountBalance.get().setBalance(newBalance);
                accountBalanceRepository.save(accountBalance.get());
            }

            transactionRepository.delete(transactionOptional.get());
        } else {
            throw new IllegalStateException("Transacción no encontrada");
        }
    }
}
