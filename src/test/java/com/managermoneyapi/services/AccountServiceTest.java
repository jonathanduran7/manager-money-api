package com.managermoneyapi.services;

import com.managermoneyapi.dto.CreateAccountDto;
import com.managermoneyapi.entity.Account;
import com.managermoneyapi.repositories.AccountRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountServiceTest {

    @Autowired
    private AccountService accountService;
    @MockBean
    private AccountRepository accountRepository;

    @Test
    @DisplayName("create account")
    void createAccount() {
        Account accountSaved = Account.builder()
                .name("Account Test")
                .created_at(LocalDate.now().toString())
                .build();

        Mockito.when(accountRepository.save(accountSaved)).thenReturn(accountSaved);

        CreateAccountDto createAccountDto = CreateAccountDto.builder()
                .name("Account Test")
                .build();

        Account accountSaveService = accountService.save(createAccountDto);

        assertEquals(accountSaveService.getName(), "Account Test");
        assertNotNull(accountSaveService.getCreated_at());
    }

    @Test
    @DisplayName("update account")
    void updateAccount() {
        Account accountToUpdate = Account.builder()
                .id(1L)
                .name("Account Test")
                .created_at(LocalDate.now().toString())
                .build();

        Mockito.when(accountRepository.findById(1L)).thenReturn(java.util.Optional.of(accountToUpdate));
        Mockito.when(accountRepository.save(accountToUpdate)).thenReturn(accountToUpdate);

        CreateAccountDto createAccountDto = CreateAccountDto.builder()
                .name("Account Test Update")
                .build();

        Account accountUpdateService = accountService.update(1L, createAccountDto);

        assertEquals(accountUpdateService.getName(), "Account Test Update");
    }

    @Test
    @DisplayName("get account by id")
    void getAccountById() {
        Account account = Account.builder()
                .id(1L)
                .name("Account Test")
                .created_at(LocalDate.now().toString())
                .build();

        Mockito.when(accountRepository.findById(1L)).thenReturn(java.util.Optional.of(account));

        Account accountServiceById = accountService.findById(1L);

        assertEquals(accountServiceById.getName(), "Account Test");
    }

    @Test
    @DisplayName("get all accounts")
    void getAllAccounts() {
        Account account = Account.builder()
                .id(1L)
                .name("Account Test")
                .created_at(LocalDate.now().toString())
                .build();

        Account account2 = Account.builder()
                .id(2L)
                .name("Account Test 2")
                .created_at(LocalDate.now().toString())
                .build();

        Mockito.when(accountRepository.findAll()).thenReturn(java.util.List.of(account, account2));

        assertEquals(accountService.findAll().size(), 2);
    }

}