package com.itonehack.smartbudget.application.service.reports.mapper;

import com.itonehack.smartbudget.application.service.reports.dto.BudgetImplementationDTO;
import com.itonehack.smartbudget.application.service.reports.dto.BudgetProportionsReportDTO;
import com.itonehack.smartbudget.domain.model.BudgetImplementationProjection;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.List;

/**
 * Mapper class for ReportService that converts entities to DTO objects.
 */
@Component
public class BudgetProportionsReportMapper {
    public BudgetProportionsReportDTO toBudgetProportionsReportDTO(
            Instant startDate,
            Instant endDate,
            BigDecimal sum,
            List<BudgetImplementationProjection> projections
    ) {
        List<BudgetImplementationDTO> budgets = projections.stream().map(projection -> {
            Double percent = projection.getAmount().multiply(BigDecimal.valueOf(100.0))
                    .divide(sum, 2, RoundingMode.CEILING).doubleValue();
            return BudgetImplementationDTO.builder()
                    .id(projection.getId())
                    .purpose(projection.getPurpose())
                    .amount(projection.getAmount())
                    .percent(percent)
                    .build();
        }).toList();

        return BudgetProportionsReportDTO.builder()
                .startDate(startDate)
                .endDate(endDate)
                .amount(sum)
                .budgets(budgets)
                .build();
    }
}
