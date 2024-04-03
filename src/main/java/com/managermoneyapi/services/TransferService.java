package com.managermoneyapi.services;

import com.managermoneyapi.dto.TransferRequestDto;
import com.managermoneyapi.dto.TransferResponseDto;

public interface TransferService extends BaseService<TransferResponseDto, TransferRequestDto>  {
    void delete(Long id);
}
