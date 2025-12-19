package com.github.novabank.application.services.customer_accounts;

import com.github.novabank.application.customer_accounts.RegisterCustomer;
import com.github.novabank.application.dtos.RegisterResult;
import com.github.novabank.domain.account.account_creation.AccountInfo;
import com.github.novabank.domain.account.accounts.Account;
import com.github.novabank.domain.finance.finance_accounts.Finance;
import com.github.novabank.presentation.dtos.RegisterRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

/**
 * Application service wrapping the customer registration process.
 * Accepts a front-end RegisterRequest and returns a RegisterResult DTO.
 */
@Service
public class RegisterApplicationService {

    private final RegisterCustomer registerCustomer;

    public RegisterApplicationService(RegisterCustomer registerCustomer) {
        this.registerCustomer = registerCustomer;
    }

    public RegisterResult execute(RegisterRequest request) throws Exception {
        if (request == null) {
            throw new IllegalArgumentException("RegisterRequest cannot be null");
        }

        // Convert age -> dateOfBirth
        LocalDate dateOfBirth = LocalDate.now().minusYears(request.getAge());

        // Map front-end DTO -> domain AccountInfo
        AccountInfo info = new AccountInfo(
                request.getEmail(),
                request.getPassword(),
                request.getFullName(),
                dateOfBirth,
                "000-000-0000" // no phone number from front-end request, but it's required
        );

        // No initial finance products or parent account
        List<Finance> initialFinanceProducts = Collections.emptyList();

        Account createdAccount = registerCustomer.registerNewCustomer(info, initialFinanceProducts, null);

        // Map domain Account -> back-end DTO
        RegisterResult result = new RegisterResult();
        result.setFullName(createdAccount.getFullName());
        result.setEmail(createdAccount.getEmail());
        result.setPassword("[PROTECTED]"); // never return raw password
        result.setAge(request.getAge());
        result.setAccountType(request.getAccountType()); // keep front-end value

        return result;
    }
}
