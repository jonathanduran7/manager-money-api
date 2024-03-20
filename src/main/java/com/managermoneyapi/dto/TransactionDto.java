package com.managermoneyapi.dto;

import com.managermoneyapi.model.TypeTransaction;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDto {
    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    private TypeTransaction type_transaction;

    private BigDecimal amount;

    private Long account_id;

    private Long category_id;

    private Long account_destination_id;

    private Long account_origin_id;
}
