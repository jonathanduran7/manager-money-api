package com.managermoneyapi.mappers;

import com.managermoneyapi.dto.TransferResponseDto;
import com.managermoneyapi.entity.Transfer;
import org.modelmapper.ModelMapper;

public class TransferResponseMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static TransferResponseDto toDTO(Transfer transfer) {
        TransferResponseDto dto = modelMapper.map(transfer, TransferResponseDto.class);
        dto.setAccountDestinationName(transfer.getAccountDestination().getName());
        dto.setAccountOriginName(transfer.getAccountOrigin().getName());
        return dto;
    }
}
