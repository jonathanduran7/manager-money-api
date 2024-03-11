package com.managermoneyapi.services;

import com.managermoneyapi.entity.Account;

import java.util.List;

public interface AccountService {
    List<Account> findAll();

    Account findById(Long id);

    Account save(Account account);

    Account update(Long id, Account account);
}
