package com.github.novabank.domain.account;


import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Josef Geshelin
 * @version 1.1
 * @since 2025-11-14
 */
public class AdultAccountBuilder implements AccountBuilder<AdultAccount> {
    private int UID;
    private String email;
    private String password;
    private String fullName;
    private LocalDate dateOfBirth;
    private String phoneNumber;


    public AdultAccountBuilder setUID(int UID) {
        this.UID = UID;
        return this;
    }
    public AdultAccountBuilder setEmail(String email) {
        this.email = email;
        return this;
    }
    public AdultAccountBuilder setPassword(String password) {
        this.password = password;
        return this;
    }
    public AdultAccountBuilder setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }
    public AdultAccountBuilder setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }
    public AdultAccountBuilder setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    @Override
    public AdultAccount build() {
        ValidationResult result = validate();
        if (!result.isValid()) {
            throw new IllegalArgumentException("Validation failed: " + String.join(", ", result.getErrors()));
        }
        return new AdultAccount(email, password, fullName,
                dateOfBirth, phoneNumber);
    }

    @Override
    public void reset() {
        this.UID = 0;
        this.email = null;
        this.password = null;
        this.fullName = null;
        this.dateOfBirth = null;
        this.phoneNumber = null;
    }

    // @Override
    // public ValidationResult validate() {
    //     AccountInfo info = new AccountInfo(this.email, this.password, this.fullName, this.dateOfBirth, this.phoneNumber);
    //     AccountInfoValidator validator = new AccountInfoValidator();
    //     ValidationResult result = validator.validate(info);
    //     List<String> errors = new ArrayList<>(result.getErrors());
    //     if (this.dateOfBirth != null && AccountInfoValidator.getAge(this.dateOfBirth) < 18) {
    //         errors.add("Account holder must be at least 18 years old.");
    //     }
    //     return errors.isEmpty() ? ValidationResult.success() : ValidationResult.failure(errors);
    // }

    @Override
    public ValidationResult validate() {
        AccountInfo info = new AccountInfo(this.email, this.password, this.fullName, this.dateOfBirth, this.phoneNumber);
        
        List<String> errors = new ArrayList<>();

        if (info.getEmail() == null || info.getEmail().trim().isEmpty()) {
            errors.add("Email cannot be empty");

        } else if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            errors.add("Email format is invalid");
        }

        if (info.getPassword() == null || info.getPassword().isEmpty()) {
            errors.add("Password cannot be empty");
        } else if (!validatePass(info.getPassword()).isValid()) {
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
        if (info.getDateOfBirth() != null && (Period.between(info.getDateOfBirth(), LocalDate.now()).getYears()) < 18) {
            errors.add("Account holder must be at least 18 years old.");
        }

        if (info.getPhoneNumber() == null || info.getPhoneNumber().trim().isEmpty()) {
            errors.add("Phone number cannot be empty");
        }


        return errors.isEmpty() ? ValidationResult.success() : ValidationResult.failure(errors);
    }

    public ValidationResult validatePass(String pass) {

        List<String> errors = new ArrayList<>();

        if (pass == null) {
            errors.add("Password cannot be empty or null.");
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

    @Override
    public String toString() {
        return String.format("AdultAccountBuilder[ email=%s password=%s fullName=%s dateOfBirth=%s phoneNumber=%s]",
        email, password, fullName, dateOfBirth, phoneNumber);
    }
}
