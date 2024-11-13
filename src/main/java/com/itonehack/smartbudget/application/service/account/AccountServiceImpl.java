package com.itonehack.smartbudget.application.service.account;

import com.itonehack.smartbudget.application.service.account.dto.AccountDTO;
import com.itonehack.smartbudget.application.service.account.mapper.AccountServiceMapper;
import com.itonehack.smartbudget.domain.exception.BadRequestException;
import com.itonehack.smartbudget.domain.exception.ForbiddenException;
import com.itonehack.smartbudget.domain.exception.NotFoundException;
import com.itonehack.smartbudget.domain.model.Account;
import com.itonehack.smartbudget.domain.model.User;
import com.itonehack.smartbudget.domain.ports.in.AccountService;
import com.itonehack.smartbudget.domain.ports.in.UserService;
import com.itonehack.smartbudget.domain.ports.out.AccountRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

/**
 * Implementation of {@link AccountService}
 */
@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {
    private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    private final AccountServiceMapper accountServiceMapper;
    private final AccountRepositoryPort accountRepositoryPort;
    private final UserService userService;

    @Override
    public Account findAccountById(Long id) {
        logger.info("Retrieving account by ID: {}", id);
        return accountRepositoryPort.findAccountById(id)
                .orElseThrow(() -> new NotFoundException("The account was not found by id " + id));
    }

    @Override
    public Account findAccountByUserIdAndAccountId(Long userId, Long accountId) {
        logger.info("Finding accounts by user id: {} and account id: {}", userId, accountId);
        return accountRepositoryPort.findAccountByUserIdAndAccountId(userId, accountId)
                .orElseThrow(() -> new NotFoundException("The account was not found by id " + accountId));
    }

    @Override
    public Account update(Account account) {
        logger.info("Updating account: {}", account);
        return accountRepositoryPort.save(account);
    }

    @Override
    public List<Account> findAccounts(String username) {
        logger.info("Retrieving accounts for user with username: {}", username);
        User foundUser = userService.findByUsername(username);
        return accountRepositoryPort.findAccountsByUserId(foundUser.getId());
    }

    @Override
    public Account save(String username, AccountDTO accountDTO) {
        logger.info("Creating account for user with username: {}", username);
        User foundUser = userService.findByUsername(username);
        Account account = accountServiceMapper.toEntity(accountDTO);
        account.setOpen(true);
        account.setUser(foundUser);
        account.setUpdateAt(Instant.now());
        return accountRepositoryPort.save(account);
    }

    @Override
    public void closeAccount(String username, Long accountId) {
        logger.info("Closing account with ID: {} for user with username: {}", accountId, username);
        Account foundAccount = findAccountById(accountId);
        if (!foundAccount.getUser().getUsername().equals(username)) {
            throw new ForbiddenException("It is forbidden to work with other people's accounts");
        }
        if (foundAccount.getBalance().compareTo(BigDecimal.ZERO) > 0) {
            throw new BadRequestException("Account balance is greater than zero");
        }
        foundAccount.setOpen(false);
        foundAccount.setUpdateAt(Instant.now());
        accountRepositoryPort.save(foundAccount);
        logger.info("Account closed successfully with ID: {}", accountId);
    }
}
