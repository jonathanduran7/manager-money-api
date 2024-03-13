package com.managermoneyapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CreateAccountDto {
    @Size(min = 3, message = "Name must have at least 3 characters")
    @NotBlank(message = "Name is required")
    private String name;
}
