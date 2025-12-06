package com.github.novabank.domain.account;

import java.time.LocalDate;
import java.time.Period;

import java.util.ArrayList;
import java.util.List;


/**
 * Validates AccountInfo before creating an Account.
 * Returns ValidationResult with detailed error messages.
 */
public class AccountInfoValidator implements Validator<AccountInfo> {

    private final ValidatePassword passwordValidator = new ValidatePassword();

    /**
     * Validates all fields in AccountInfo.
     * @param info AccountInfo to validate
     * @return ValidationResult with success status and any error messages
     */
    @Override
    public ValidationResult validate(AccountInfo info) {
        List<String> errors = new ArrayList<>();

        if (info.getEmail() == null || info.getEmail().trim().isEmpty()) {
            errors.add("Email cannot be empty");
        } else if (!isValidEmail(info.getEmail())) {
            errors.add("Email format is invalid");
        }

        if (info.getPassword() == null || info.getPassword().isEmpty()) {
            errors.add("Password cannot be empty");
        } else if (!passwordValidator.validate(info.getPassword()).isValid()) {
            errors.add("Password must be at least 8 characters, contain no whitespace, and include at least one special character");
        }

        if (info.getFullName() == null || info.getFullName().trim().isEmpty()) {
            errors.add("Full name cannot be empty");
        }

        if (info.getDateOfBirth() == null) {
            errors.add("Date of birth cannot be empty");
        } else if (info.getDateOfBirth().isAfter(LocalDate.now())) {
            errors.add("Date of birth cannot be in the future");
        }

        if (info.getPhoneNumber() == null || info.getPhoneNumber().trim().isEmpty()) {
            errors.add("Phone number cannot be empty");
        }

        return errors.isEmpty() ? ValidationResult.success() : ValidationResult.failure(errors);
    }

    private boolean isValidEmail(String email) {
        // Simple regex for basic email validation
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }

    public static int getAge(LocalDate dateOfBirth) {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }
}