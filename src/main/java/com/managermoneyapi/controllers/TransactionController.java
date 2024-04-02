package com.managermoneyapi.controllers;

import com.managermoneyapi.dto.TransactionDto;
import com.managermoneyapi.dto.TransactionResponseDto;
import com.managermoneyapi.entity.Transaction;
import com.managermoneyapi.services.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/transactions")
@Validated
public class TransactionController extends BaseController<TransactionResponseDto, TransactionDto, TransactionService>{

    @DeleteMapping("/{id}")
    @ResponseBody
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
