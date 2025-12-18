package com.github.novabank.application.customer_accounts;

import com.github.novabank.domain.account.account_creation.AdultAccountBuilder;
import com.github.novabank.domain.account.account_creation.ChildAccountBuilder;
import com.github.novabank.domain.account.account_creation.ValidationResult;
import com.github.novabank.domain.account.accounts.Account;
import com.github.novabank.domain.account.accounts.AdultAccount;
import com.github.novabank.domain.account.accounts.ChildAccount;
import com.github.novabank.infrastructure.database.AccountRepositoryimpl;

import java.util.ArrayList;
import java.util.List;

public class ChangePassword {

    public static Account changePassword(String email, String newPass) throws Exception {
        
        AccountRepositoryimpl repository = new AccountRepositoryimpl();
        Account account = repository.findByEmail(email);

        if (account == null) {
            throw new IllegalArgumentException("No account found with the specified email address.");
        }


        ValidationResult result = validatePass(newPass);
        if (!result.isValid()) {
            throw new IllegalArgumentException("Invalid password: " + String.join(", ", result.getErrors()));
        }

        repository.update(account, "password", newPass);

        if (account instanceof AdultAccount) {
            AdultAccountBuilder builder = new AdultAccountBuilder()
                    .setFullName(account.getFullName())
                    .setEmail(account.getEmail())
                    .setDateOfBirth(account.getDateOfBirth())
                    .setPhoneNumber(account.getPhoneNumber())
                    .setPassword(newPass);
            account.getFinanceProducts().values().forEach(builder::addFinanceProduct);
            return builder.build();
        } else if (account instanceof ChildAccount) {
            ChildAccountBuilder builder = new ChildAccountBuilder()
                    .setFullName(account.getFullName())
                    .setEmail(account.getEmail())
                    .setDateOfBirth(account.getDateOfBirth())
                    .setPhoneNumber(account.getPhoneNumber())
                    .setPassword(newPass);
            account.getFinanceProducts().values().forEach(builder::addFinanceProduct);
            return builder.build();
        } else {
            throw new IllegalStateException("Unknown account type");
        }
    }

    public static ValidationResult validatePass(String pass) {
        List<String> errors = new ArrayList<>();

        if (pass == null || pass.isEmpty()) {
            errors.add("Password cannot be empty or null.");
            return ValidationResult.failure(errors);
        }

        if (pass.length() < 8) {
            errors.add("Password doesn't meet minimum length (at least 8 characters).");
        }

        if (pass.contains(" ") || pass.matches(".*\\s.*")) {
            errors.add("Password must NOT have any whitespace.");
        }

        if (!pass.matches(".*[!@#$%^&*()_+\\-=\\[\\]{}|;:',.<>?/~`].*")) {
            errors.add("Password must have at least one special character.");
        }

        return errors.isEmpty() ? ValidationResult.success() : ValidationResult.failure(errors);
    }
}