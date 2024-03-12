package com.managermoneyapi.services;

import com.managermoneyapi.dto.CreateAccountDto;
import com.managermoneyapi.entity.Account;

import java.util.List;

public interface AccountService {
    List<Account> findAll();

    Account findById(Long id);

    Account save(CreateAccountDto accountDto);

    Account update(Long id, CreateAccountDto accountDto);
}
