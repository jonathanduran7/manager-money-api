package com.managermoneyapi.controllers;

import com.managermoneyapi.dto.TransactionDto;
import com.managermoneyapi.entity.Transaction;
import com.managermoneyapi.services.TransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/transactions")
@Validated
public class TransactionController extends BaseController<Transaction, TransactionDto, TransactionService>{
}
