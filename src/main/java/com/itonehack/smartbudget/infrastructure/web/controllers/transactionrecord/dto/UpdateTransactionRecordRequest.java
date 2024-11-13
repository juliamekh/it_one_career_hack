package com.itonehack.smartbudget.infrastructure.web.controllers.transactionrecord.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateTransactionRecordRequest {
    @NotNull
    private Long id;
    @DecimalMin(value = "0")
    @NotNull
    private BigDecimal amount;
    @NotNull
    private String description;
    @NotNull
    private Long categoryId;
    @NotNull
    private Long budgetId;
}
