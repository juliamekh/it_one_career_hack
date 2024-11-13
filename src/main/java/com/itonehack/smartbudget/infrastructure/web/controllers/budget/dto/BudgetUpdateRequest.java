package com.itonehack.smartbudget.infrastructure.web.controllers.budget.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

/**
 * DTO for {@link com.itonehack.smartbudget.domain.model.Budget}
 */
@Data
public class BudgetUpdateRequest {
    @DecimalMin(value = "0")
    @NotNull
    private Long id;
    @DecimalMin(value = "0")
    @NotNull
    private BigDecimal limit;
    @DecimalMin(value = "0")
    @NotNull
    private Long importance;
    @NotNull
    private String purpose;
    private String cron;

}
