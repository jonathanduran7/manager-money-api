package com.managermoneyapi.controllers;

import com.managermoneyapi.entity.Account;
import com.managermoneyapi.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/accounts")
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
    public String show() {
        return "account show";
    }

    @PostMapping
    @ResponseBody
    public String create() {
        return "account create";
    }

    @PutMapping("/{id}")
    @ResponseBody
    public String update(@PathVariable Long id) {
        return "account update" + id;
    }
}
