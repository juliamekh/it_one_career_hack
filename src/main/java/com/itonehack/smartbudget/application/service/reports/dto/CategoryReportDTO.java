package com.itonehack.smartbudget.application.service.reports.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CategoryReportDTO {
    private Long categoryId;
    private String name;
    private List<CategorySpendDTO> spends;
}
