package com.itonehack.smartbudget.application.service.reports;

import com.itonehack.smartbudget.application.service.reports.dto.BankrollChangesReportDTO;
import com.itonehack.smartbudget.application.service.reports.dto.BudgetProportionsReportDTO;
import com.itonehack.smartbudget.application.service.reports.dto.CategorySpendReportDTO;
import com.itonehack.smartbudget.application.service.reports.mapper.BankrollChangesReportMapper;
import com.itonehack.smartbudget.application.service.reports.mapper.BudgetProportionsReportMapper;
import com.itonehack.smartbudget.application.service.reports.mapper.CategoryReportMapper;
import com.itonehack.smartbudget.domain.model.BankrollChangesReportProjection;
import com.itonehack.smartbudget.domain.model.BudgetImplementationProjection;
import com.itonehack.smartbudget.domain.model.CategoryReportProjection;
import com.itonehack.smartbudget.domain.ports.in.ReportService;
import com.itonehack.smartbudget.domain.ports.out.BankrollChangesRepositoryPort;
import com.itonehack.smartbudget.domain.ports.out.BudgetProportionsReportRepositoryPort;
import com.itonehack.smartbudget.domain.ports.out.CategoryReportRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReportServiceImpl implements ReportService {
    private static final Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);

    private final CategoryReportRepositoryPort categoryReportRepositoryPort;
    private final CategoryReportMapper categoryReportMapper;

    private final BankrollChangesRepositoryPort bankrollChangesRepositoryPort;
    private final BankrollChangesReportMapper bankrollChangesReportMapper;

    private final BudgetProportionsReportRepositoryPort budgetProportionsReportRepositoryPort;
    private final BudgetProportionsReportMapper budgetProportionsReportMapper;

    @Override
    public CategorySpendReportDTO getCategoriesReport(String username, Instant startDate, Instant endDate, Double step) {
        logger.debug("Generating categories report : {} {} {} {}", username, startDate, endDate, step);
        List<CategoryReportProjection> categoryReportList = categoryReportRepositoryPort.getReport(username, startDate, endDate, step);
        return categoryReportMapper.toDTO(startDate, endDate, step, categoryReportList);
    }

    @Override
    public BankrollChangesReportDTO getBankrollChangesReport(String username, Instant startDate, Instant endDate, Double step) {
        logger.debug("Generating bankroll report : {} {} {} {}", username, startDate, endDate, step);
        List<BankrollChangesReportProjection> bankrollChanges = bankrollChangesRepositoryPort.getReport(username, startDate, endDate, step);
        return bankrollChangesReportMapper.toBankrollChangesReportDTO(startDate, endDate, step, bankrollChanges);
    }

    @Override
    public BudgetProportionsReportDTO getBudgetProportionsReport(String username, Instant startDate, Instant endDate) {
        logger.debug("Generating budget implementation percentage: {} {} {}", username, startDate, endDate);
        List<BudgetImplementationProjection> projections = budgetProportionsReportRepositoryPort.getReport(username, startDate, endDate);
        BigDecimal sum = budgetProportionsReportRepositoryPort.getSumImplementation(username, startDate, endDate);
        return budgetProportionsReportMapper.toBudgetProportionsReportDTO(startDate, endDate, sum, projections);
    }
}
