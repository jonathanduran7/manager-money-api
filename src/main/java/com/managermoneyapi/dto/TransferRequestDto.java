package com.managermoneyapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransferRequestDto {
    private BigDecimal amount;
    private Long accountOrigin;
    private Long accountDestination;
}