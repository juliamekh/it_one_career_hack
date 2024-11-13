package com.itonehack.smartbudget.infrastructure.web.controllers.transactionrecord.mapper;

import com.itonehack.smartbudget.application.service.transactionrecord.dto.TransactionRecordDTO;
import com.itonehack.smartbudget.infrastructure.web.controllers.transactionrecord.dto.CreateTransactionRecordRequest;
import com.itonehack.smartbudget.infrastructure.web.controllers.transactionrecord.dto.UpdateTransactionRecordRequest;
import org.springframework.stereotype.Component;

@Component
public class TransactionRecordControllerMapper {

    public TransactionRecordDTO toTransactionRecordDTO(CreateTransactionRecordRequest createTransactionRecordRequest) {
        return TransactionRecordDTO.builder()
                .description(createTransactionRecordRequest.getDescription())
                .amount(createTransactionRecordRequest.getAmount())
                .categoryId(createTransactionRecordRequest.getCategoryId())
                .budgetId(createTransactionRecordRequest.getBudgetId())
                .build();
    }

    public TransactionRecordDTO toTransactionRecordDTO(UpdateTransactionRecordRequest updateTransactionRecordRequest) {
        return TransactionRecordDTO.builder()
                .id(updateTransactionRecordRequest.getId())
                .description(updateTransactionRecordRequest.getDescription())
                .amount(updateTransactionRecordRequest.getAmount())
                .categoryId(updateTransactionRecordRequest.getCategoryId())
                .budgetId(updateTransactionRecordRequest.getBudgetId())
                .build();
    }
}
