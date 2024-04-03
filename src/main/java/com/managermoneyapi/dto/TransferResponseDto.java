package com.managermoneyapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferResponseDto {
    private Long id;
    private BigDecimal amount;
    private String accountOriginName;
    private String accountDestinationName;
    private String date_time;
}
