package com.github.novabank.application.customer_accounts;

import com.github.novabank.domain.account.AccountRepository;
import com.github.novabank.domain.account.accounts.Account;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

/**
 * Application service for handling customer login.
 */
@Service
public class LoginCustomer {

    private final AccountRepository repository;

    public LoginCustomer(AccountRepository repository) {
        this.repository = repository;
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
