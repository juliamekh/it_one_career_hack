package com.itonehack.smartbudget.infrastructure.web.controllers.account.mapper;

import com.itonehack.smartbudget.application.service.account.dto.AccountDTO;
import com.itonehack.smartbudget.infrastructure.web.controllers.account.dto.AccountCreateRequest;
import org.springframework.stereotype.Component;

/**
 * This class is responsible for mapping AccountCreateRequest to AccountDTO.
 */
@Component
public class AccountControllerMapper {

    /**
     * Maps AccountCreateRequest to AccountDTO.
     *
     * @param accountCreateRequest The account save request object.
     * @return AccountDTO containing the balance, name, and open date from the account save request.
     */
    public AccountDTO toAccountDTO(AccountCreateRequest accountCreateRequest) {
        return AccountDTO.builder()
                .balance(accountCreateRequest.getBalance())
                .name(accountCreateRequest.getName())
                .build();
    }
}
