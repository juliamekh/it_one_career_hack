package com.itonehack.smartbudget.domain.ports.out;

import com.itonehack.smartbudget.domain.model.TransactionType;

import java.util.Optional;

/**
 * Port interface for accessing the transaction type repository.
 */
public interface TransactionTypeRepositoryPort {

    /**
     * Find a transaction type by its ID.
     *
     * @param id The ID of the transaction type to find.
     * @return An optional containing the transaction type found, or empty if not found.
     */
    Optional<TransactionType> findById(Long id);
}
