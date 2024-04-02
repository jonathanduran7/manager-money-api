package com.managermoneyapi.controllers;

import com.managermoneyapi.dto.TransactionDto;
import com.managermoneyapi.entity.Transaction;
import com.managermoneyapi.services.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/transactions")
@Validated
public class TransactionController extends BaseController<Transaction, TransactionDto, TransactionService>{

    @DeleteMapping("/{id}")
    @ResponseBody
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
