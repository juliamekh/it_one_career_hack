package com.itonehack.smartbudget.application.service.transactiontype;

import com.itonehack.smartbudget.domain.exception.NotFoundException;
import com.itonehack.smartbudget.domain.model.TransactionType;
import com.itonehack.smartbudget.domain.ports.in.TransactionTypeService;
import com.itonehack.smartbudget.domain.ports.out.TransactionTypeRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TransactionTypeServiceImpl implements TransactionTypeService {
    private static final Logger logger = LoggerFactory.getLogger(TransactionTypeServiceImpl.class);

    private final TransactionTypeRepositoryPort transactionTypeRepositoryPort;

    @Override
    public TransactionType findById(Long id) {
        logger.info("Finding transaction type with ID: {}", id);
        return transactionTypeRepositoryPort.findById(id)
                .orElseThrow(() -> {
                    String message = "Transaction type not found by id: " + id;
                    logger.error(message);
                    return new NotFoundException(message);
                });
    }
}
