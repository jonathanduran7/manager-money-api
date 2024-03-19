package com.managermoneyapi.dto;

import com.managermoneyapi.model.TypeTransaction;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDto {
    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    @NotBlank(message = "Type transaction is required")
    private TypeTransaction type_transaction;

    @NotBlank(message = "Amount is required")
    private Float amount;
}
