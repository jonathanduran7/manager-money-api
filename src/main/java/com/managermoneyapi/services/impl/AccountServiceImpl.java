package com.managermoneyapi.services.impl;

import com.managermoneyapi.entity.Account;
import com.managermoneyapi.repositories.AccountRepository;
import com.managermoneyapi.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account update(Long id, Account account) {
        Account accountToUpdate = accountRepository.findById(id).orElse(null);

        if (accountToUpdate != null) {
            accountToUpdate.setName(account.getName());
            return accountRepository.save(accountToUpdate);
        }

        return null;
    }
}
