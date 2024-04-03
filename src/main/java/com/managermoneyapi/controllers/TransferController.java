package com.managermoneyapi.controllers;

import com.managermoneyapi.dto.TransferRequestDto;
import com.managermoneyapi.dto.TransferResponseDto;
import com.managermoneyapi.entity.Transfer;
import com.managermoneyapi.services.TransferService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/transfers")
@Validated
public class TransferController extends BaseController<TransferResponseDto, TransferRequestDto, TransferService> {

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
