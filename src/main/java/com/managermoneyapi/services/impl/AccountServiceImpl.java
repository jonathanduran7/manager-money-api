package com.managermoneyapi.services.impl;

import com.managermoneyapi.dto.CreateAccountDto;
import com.managermoneyapi.entity.Account;
import com.managermoneyapi.repositories.AccountRepository;
import com.managermoneyapi.services.AccountService;
import org.hibernate.type.descriptor.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Optional<Account> findById(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    public Account save(CreateAccountDto accountDto) {
        Account newAccount = Account
                .builder()
                .name(accountDto.getName())
                .created_at(LocalDate.now().toString())
                .build();
        return accountRepository.save(newAccount);
    }

    @Override
    public Optional<Account> update(Long id, CreateAccountDto accountDto) {
        Account accountToUpdate = accountRepository.findById(id).get();

        if (accountToUpdate.getId() != null) {
            accountToUpdate.setName(accountDto.getName());
            return Optional.of(accountRepository.save(accountToUpdate));
        }

        return null;
    }
}
