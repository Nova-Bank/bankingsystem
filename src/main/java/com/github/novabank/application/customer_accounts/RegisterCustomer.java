package com.github.novabank.application.customer_accounts;

import com.github.novabank.domain.account.AccountRepository;
import com.github.novabank.domain.account.account_creation.AccountFactory;
import com.github.novabank.domain.account.account_creation.AccountInfo;
import com.github.novabank.domain.account.account_creation.ValidationResult;
import com.github.novabank.domain.account.accounts.Account;
import com.github.novabank.domain.account.accounts.AdultAccount;
import com.github.novabank.domain.finance.finance_accounts.Finance;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Application service for handling new customer registration.
 */
@Service
public class RegisterCustomer {

    private final AccountRepository accountRepository;

    public RegisterCustomer(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * Registers a new customer by creating an account.
     * This method takes all required information to create a complete account.
     *
     * @param info           The account information including email, password, name, DOB, and phone.
     * @param financeProducts A list of initial finance products for the account.
     * @param parent         An optional parent account if registering a child account.
     * @return The newly created Account object.
     * @throws Exception if validation fails or an error occurs during creation.
     */
    public Account registerNewCustomer(AccountInfo info, List<Finance> financeProducts, AdultAccount parent) throws Exception {
        ValidationResult validation = validateRegistrationDetails(info);
        if (!validation.isValid()) {
            throw new IllegalArgumentException("Invalid registration details: " + String.join(", ", validation.getErrors()));
        }

        Account account = AccountFactory.createAccount(info, financeProducts, parent);
        accountRepository.create(account);
        return account;
    }

    /**
     * Validates the initial and essential details for registration.
     *
     * @param info The account information to validate.
     * @return A ValidationResult indicating success or failure.
     */
    public ValidationResult validateRegistrationDetails(AccountInfo info) {
        List<String> errors = new ArrayList<>();

        if (info.getEmail() == null || info.getEmail().trim().isEmpty()) {
            errors.add("Email cannot be empty");
        } else if (!info.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            errors.add("Email format is invalid");
        }

        if (info.getPassword() == null || info.getPassword().isEmpty()) {
            errors.add("Password cannot be empty");
        } else {
            ValidationResult passwordValidation = validatePassword(info.getPassword());
            if (!passwordValidation.isValid()) {
                errors.addAll(passwordValidation.getErrors());
            }
        }
        
        if (info.getFullName() == null || info.getFullName().trim().isEmpty()) {
            errors.add("Full name cannot be empty");
        }
        if (info.getDateOfBirth() == null) {
            errors.add("Date of birth cannot be null");
        }
        if (info.getPhoneNumber() == null || info.getPhoneNumber().trim().isEmpty()) {
            errors.add("Phone number cannot be empty");
        }


        return errors.isEmpty() ? ValidationResult.success() : ValidationResult.failure(errors);
    }

    private ValidationResult validatePassword(String pass) {
        List<String> errors = new ArrayList<>();
        if (pass.length() < 8) {
            errors.add("Password must be at least 8 characters.");
        }
        if (pass.contains(" ") || pass.matches(".*\\s.*")) {
            errors.add("Password must not contain whitespace.");
        }
        if (!pass.matches(".*[!@#$%^&*()_+\\-=\\[\\]{}|;:',.<>?/~`].*")) {
            errors.add("Password must have at least one special character.");
        }
        return errors.isEmpty() ? ValidationResult.success() : ValidationResult.failure(errors);
    }
}
