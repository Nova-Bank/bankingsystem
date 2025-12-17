package com.github.novabank.application.customer_accounts;

import com.github.novabank.domain.account.accounts.Account;
import com.github.novabank.infrastructure.database.AccountRepositoryimpl;

import java.util.concurrent.ExecutionException;

/**
 * Application service for handling customer login.
 */
public class LoginCustomer {

    private final AccountRepositoryimpl repository;

    public LoginCustomer() {
        this.repository = new AccountRepositoryimpl();
    }

    /**
     * Attempts to log in a customer using an identifier (email or phone number) and a password.
     *
     * @param identifier The customer's email or phone number.
     * @param password   The customer's password.
     * @return The authenticated Account object if successful, otherwise null.
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public Account login(String identifier, String password) throws Exception {
        if (identifier == null || identifier.trim().isEmpty() || password == null || password.isEmpty()) {
            return null;
        }

        Account account = repository.findByEmail(identifier);
        if (account == null) {
            account = repository.findByPhoneNumber(identifier);
        }

        if (account != null) {
            if (account.getPassword().equals(password)) {
                return account;
            }
        }

        return null;
    }
}
