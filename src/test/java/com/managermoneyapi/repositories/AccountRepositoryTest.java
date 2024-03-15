package com.managermoneyapi.repositories;

import com.managermoneyapi.entity.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    private Account firstAccount;

    @BeforeEach
    void setup(){
        firstAccount = Account.builder()
                .name("Account Test 1")
                .created_at("2022-12-01")
                .build();

        accountRepository.save(firstAccount);
    }

    @Test
    @DisplayName("Save Account")
    void testSaveAccount() {
        Account account = Account.builder()
                .name("Account Test")
                .created_at("2021-08-01")
                .build();

        Account accountSaved = accountRepository.save(account);

        assertThat(accountSaved).isNotNull();
        assertThat(accountSaved.getName()).isEqualTo("Account Test");
    }

    @Test
    @DisplayName("Get all accounts")
    void testGetAllAccounts(){
        Account secondAccount = Account.builder()
                .name("Account Test 2")
                .created_at("2022-12-01")
                .build();

        accountRepository.save(secondAccount);

        assertThat(accountRepository.findAll()).isNotEmpty();
        assertThat(accountRepository.findAll().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("Get account by id")
    void testGetAccountById(){
        Account account = accountRepository.findById(firstAccount.getId()).get();

        assertThat(account).isNotNull();
        assertThat(account.getName()).isEqualTo("Account Test 1");
    }

    @Test
    @DisplayName("Update account")
    void testUpdateAccount(){
        Account account = accountRepository.findById(firstAccount.getId()).get();
        account.setName("Account Test 1 Updated");

        Account accountUpdated = accountRepository.save(account);

        assertThat(accountUpdated).isNotNull();
        assertThat(accountUpdated.getName()).isEqualTo("Account Test 1 Updated");
    }
}

