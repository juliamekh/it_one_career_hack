package com.itonehack.smartbudget.infrastructure.jpa.transactiontype;

import com.itonehack.smartbudget.domain.model.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaTransactionTypeRepository extends JpaRepository<TransactionType, Long> {
}
