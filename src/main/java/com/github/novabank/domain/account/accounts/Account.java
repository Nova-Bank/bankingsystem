package com.github.novabank.domain.account.accounts;

import com.github.novabank.domain.finance.Finance;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Map;
import java.util.Random;

/**
 * Abstract base class for all account types.
 * Immutable after creation - no setters for critical fields.
 * Now depends on primitives instead of AccountInfo (Dependency Inversion)
 */
public abstract class Account {

    /*
     * Should we Final from email, password & phoneNumber as they can be changed if they get a new phone,email or want to change password?
    */

    private final int UID;
    private final String email;
    private final String password;
    private final String fullName;
    private final LocalDate dateOfBirth;
    private final String phoneNumber;
    private final Map<String, Finance> financeProducts;

    protected Account(String email, String password, String fullName,
                     LocalDate dateOfBirth, String phoneNumber, Map<String, Finance> financeProducts) {
        Random random = new Random();
        this.UID = random.nextInt(900000) + 100000; // 6-digit random number
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.financeProducts = financeProducts;
    }

    public int getUID() {
        return this.UID;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public String getFullName() {
        return this.fullName;
    }

    public LocalDate getDateOfBirth() {
        return this.dateOfBirth;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public Map<String, Finance> getFinanceProducts() {
        return Collections.unmodifiableMap(financeProducts);
    }

    /**
     * Verify login credentials.
     * 
     * @param email Email to verify
     * @param password Password to verify
     * @return true if credentials match
     * @apiNote WARNING: This is not secure! Passwords should be hashed and compared securely.
     */
    public boolean login(String email, String password) {
        return this.email.equals(email) && this.password.equals(password);
    }

    // add toString()



}