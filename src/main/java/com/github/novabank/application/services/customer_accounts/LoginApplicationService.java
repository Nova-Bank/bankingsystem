package com.github.novabank.application.services.customer_accounts;

import com.github.novabank.application.customer_accounts.LoginCustomer;
import com.github.novabank.application.dtos.LoginResult;
import com.github.novabank.presentation.dtos.LoginRequest;
import com.github.novabank.domain.account.accounts.Account;

/**
 * Application service wrapping the login process.
 * Accepts a front-end LoginRequest and returns a LoginResult.
 */
public class LoginApplicationService {

    private final LoginCustomer loginCustomer;

    public LoginApplicationService() {
        this.loginCustomer = new LoginCustomer();
    }

    /**
     * Executes the login process for the given request.
     *
     * @param request LoginRequest containing email/phone and password
     * @return LoginResult containing email and protected password
     * @throws Exception if repository access fails
     */
    public LoginResult execute(LoginRequest request) throws Exception {
        if (request == null) {
            throw new IllegalArgumentException("LoginRequest cannot be null");
        }

        Account account = loginCustomer.login(request.getEmail(), request.getPassword());

        if (account == null) {
            return null; // login failed
        }

        // Map domain Account -> back-end DTO
        LoginResult result = new LoginResult();
        result.setEmail(account.getEmail());
        result.setPassword("[PROTECTED]"); // Never return actual password

        return result;
    }
}
