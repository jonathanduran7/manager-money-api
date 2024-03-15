package com.managermoneyapi.controllers;

import com.managermoneyapi.dto.CreateAccountDto;
import com.managermoneyapi.entity.Account;
import com.managermoneyapi.services.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/accounts")
@Validated
public class AccountController extends BaseController<Account, CreateAccountDto, AccountService>{
}
