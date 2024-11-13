package com.itonehack.smartbudget.application.service.budget.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * DTO for {@link com.itonehack.smartbudget.domain.model.Budget}
 */
@Data
@Builder
public class BudgetDTO {
    private Long id;
    private BigDecimal limit;
    private Long importance;
    private String purpose;
    private String cron;

}