package com.github.novabank.builder;

import com.github.novabank.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Josef Geshelin
 * @version 1.1
 * @since 2025-11-14
 */
public class ChildAccountBuilder implements AccountBuilder<ChildAccount> {

    private int UID;
    private String email;
    private String password;
    private String fullName;
    private LocalDate dateOfBirth;
    private String phoneNumber;

    public ChildAccountBuilder setUID(int UID) {
        this.UID = UID;
        return this;
    }
    public ChildAccountBuilder setEmail(String email) {
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
    public ChildAccount build() {
        ValidationResult result = validate();
        if (!result.isValid()) {
            throw new IllegalArgumentException("Validation failed: " + String.join(", ", result.getErrors()));
        }
        return new ChildAccount(email, password, fullName, dateOfBirth, phoneNumber);
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

    @Override
    public ValidationResult validate() {
        AccountInfo info = new AccountInfo(this.email, this.password, this.fullName, this.dateOfBirth, this.phoneNumber);
        AccountInfoValidator validator = new AccountInfoValidator();
        ValidationResult result = validator.validate(info);

        List<String> errors = new ArrayList<>(result.getErrors());

        if (this.dateOfBirth != null && AccountInfoValidator.getAge(this.dateOfBirth) >= 18) {
            errors.add("Account holder must be under 18 years old.");
        }

        return errors.isEmpty() ? ValidationResult.success() : ValidationResult.failure(errors);
    }

    public String toString() {
        return String.format("ChildAccountBuilder[ID=%d, email=%s, password=%s, fullName=%s, dateOfBirth=%s, phoneNumber=%s]",
                UID, email, password, fullName, dateOfBirth, phoneNumber);
    }
}
