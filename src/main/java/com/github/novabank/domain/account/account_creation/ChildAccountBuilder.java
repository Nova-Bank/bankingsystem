package com.github.novabank.domain.account.account_creation;

import com.github.novabank.domain.account.accounts.ChildAccount;
import com.github.novabank.domain.finance.Finance;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Josef Geshelin
 * @version 1.1
 * @since 2025-11-14
 */
public class ChildAccountBuilder implements AccountBuilder<ChildAccount> {

    private String email;   
    private String password; 
    private String fullName;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private Map<String, Finance> financeProducts = new HashMap<>();

    
    public ChildAccountBuilder setEmail(String email){
        this.email = email;
        return this;
    }
    public ChildAccountBuilder setPassword(String password) {
        this.password = password;
        return this;
    }
    public ChildAccountBuilder setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }
    public ChildAccountBuilder setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }
    public ChildAccountBuilder setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    @Override
    public AccountBuilder<ChildAccount> addFinanceProduct(Finance financeProduct) {
        this.financeProducts.put(financeProduct.getClass().getSimpleName(), financeProduct);
        return this;
    }

    @Override
    public ChildAccount build() {
        ValidationResult result = validate();
        if (!result.isValid()) {
            throw new IllegalArgumentException("Validation failed: " + String.join(", ", result.getErrors()));
        }
        return new ChildAccount(email, password, fullName, dateOfBirth, phoneNumber, financeProducts);
    }

    @Override
    public void reset(){
        this.email = null;
        this.password = null;
        this.fullName = null;
        this.dateOfBirth = null;
        this.phoneNumber = null;
        this.financeProducts.clear();
    }

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
        if (info.getDateOfBirth() != null && (Period.between(info.getDateOfBirth(), LocalDate.now()).getYears()) >= 18) {
            errors.add("Account holder must be under 18 years old.");
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
        return String.format("ChildAccountBuilder[email=%s password=%s fullName=%s dateOfBirth=%s phoneNumber=%s]",
        email, password, fullName, dateOfBirth, phoneNumber);
    }
}
