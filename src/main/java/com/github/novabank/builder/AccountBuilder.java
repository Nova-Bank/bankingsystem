package main.java.com.github.novabank.builder;

import java.time.LocalDate;

import main.java.com.github.novabank.model.AccountInfo;
import main.java.com.github.novabank.model.AccountInfoValidator;
import main.java.com.github.novabank.model.AdultAccount;
import main.java.com.github.novabank.model.ChildAccount;
import main.java.com.github.novabank.model.ValidationResult;

/**
 * Builder class for creating Account instances with a fluent API.
 * Handles validation and provides clear error messages.
 * 
 * Usage:
 * <pre>
 * AdultAccount account = new AccountBuilder()
 *     .email("user@example.com")
 *     .password("SecurePass123!")
 *     .fullName("John Doe")
 *     .dateOfBirth(LocalDate.of(1990, 5, 15))
 *     .phoneNumber("555-1234")
 *     .buildAdult();
 * </pre>
 */
public class AccountBuilder {
    
    private String email;
    private String password;
    private String fullName;
    private LocalDate dateOfBirth;
    private String phoneNumber;

    private AccountInfo createAccountInfo() {
        return new AccountInfo(email, password, fullName, dateOfBirth, phoneNumber);
    }
    
    public AccountBuilder email(String email) {
        this.email = email;
        return this;
    }

    public AccountBuilder password(String password) {
        this.password = password;
        return this;
    }

    public AccountBuilder fullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public AccountBuilder dateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public AccountBuilder dateOfBirth(int year, int month, int day) {
        this.dateOfBirth = LocalDate.of(year, month, day);
        return this;
    }

    public AccountBuilder phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    /**
     * Build an AdultAccount (18+).
     * 
     * @return New AdultAccount instance
     * @throws IllegalArgumentException if validation fails or age < 18
     */
    public AdultAccount buildAdult() throws IllegalArgumentException {
        AccountInfo info = createAccountInfo();
        return AdultAccount.create(info);
    }

    /**
     * Build a ChildAccount (under 18) linked to a parent.
     * 
     * @param parent Parent adult account
     * @return New ChildAccount instance
     * @throws IllegalArgumentException if validation fails or age >= 18
     */
    public ChildAccount buildChild(AdultAccount parent) throws IllegalArgumentException {
        AccountInfo info = createAccountInfo();
        return ChildAccount.create(info, parent);
    }

    /**
     * Validate the current builder state without creating an account.
     * Useful for checking if data is valid before submission.
     * 
     * @return ValidationResult with success status and any errors
     */
    public ValidationResult validate() {
        try {
            AccountInfo info = createAccountInfo();
            AccountInfoValidator validator = new AccountInfoValidator();
            return validator.validate(info);
        } catch (Exception e) {
            return ValidationResult.failure("Failed to validate: " + e.getMessage());
        }
    }

    /**
     * Check if the builder can create an adult account (age >= 18).
     * 
     * @return true if age requirement is met
     */
    public boolean canBuildAdult() {
        if (dateOfBirth == null) {
            return false;
        }
        int age = AccountInfoValidator.getAge(dateOfBirth);
        return age >= 18;
    }

    /**
     * Check if the builder can create a child account (age <= 17).
     * 
     * @return true if age requirement is met
     */
    public boolean canBuildChild() {
        if (dateOfBirth == null) {
            return false;
        }
        int age = AccountInfoValidator.getAge(dateOfBirth);
        return age <= 17;
    }

    /**
     * Reset the builder to initial state.
     */
    public AccountBuilder reset() {
        this.email = null;
        this.password = null;
        this.fullName = null;
        this.dateOfBirth = null;
        this.phoneNumber = null;
        return this;
    }

}