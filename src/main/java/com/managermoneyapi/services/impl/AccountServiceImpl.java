package com.managermoneyapi.services.impl;

import com.managermoneyapi.dto.CreateAccountDto;
import com.managermoneyapi.entity.Account;
import com.managermoneyapi.repositories.AccountRepository;
import com.managermoneyapi.services.AccountService;
import org.hibernate.type.descriptor.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account findById(Long id) {
        return accountRepository.findById(id).orElse(null);
    }

    @Override
    public Account save(CreateAccountDto accountDto) {
        Account newAccount = new Account()
                .builder()
                .name(accountDto.getName())
                .created_at(LocalDateTime.now().toString())
                .build();
        return accountRepository.save(newAccount);
    }

    @Override
    public Account update(Long id, CreateAccountDto accountDto) {
        Account accountToUpdate = accountRepository.findById(id).orElse(null);

        if (accountToUpdate != null) {
            accountToUpdate.setName(accountDto.getName());
            return accountRepository.save(accountToUpdate);
        }

        return null;
    }
}
