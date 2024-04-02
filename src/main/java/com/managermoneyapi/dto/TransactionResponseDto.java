package com.managermoneyapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponseDto {
    private Long id;
    private String title;
    private String description;
    private BigDecimal amount;
    private String accountName;
    private String date_time;
    private String categoryName;
}
