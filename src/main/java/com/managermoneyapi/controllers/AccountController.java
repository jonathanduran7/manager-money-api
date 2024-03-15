package com.managermoneyapi.controllers;

import com.managermoneyapi.dto.CreateAccountDto;
import com.managermoneyapi.entity.Account;
import com.managermoneyapi.services.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/accounts")
@Validated
public class AccountController {

    @Autowired
    public AccountService accountService;

    @GetMapping
    @ResponseBody
    public List<Account> list() {
        return accountService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> show(@PathVariable Long id) {
        Account account = accountService.findById(id).get();
        if (account.getId() != null) {
            return ResponseEntity.ok(account);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not found");
        }
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Account> create(@RequestBody @Valid CreateAccountDto accountDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.save(accountDto));
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid CreateAccountDto accountDto) {
        Account account = accountService.update(id, accountDto).get();
        if(account.getId() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not found");
        }
        return ResponseEntity.ok(account);
    }
}
