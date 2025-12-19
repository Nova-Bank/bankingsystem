package com.github.novabank.domain.account.accounts;

import com.github.novabank.domain.finance.finance_accounts.Finance;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Map;
import java.util.Random;

/**
 * Abstract base class for all account types.
 * Immutable identity + core info.
 */
public abstract class Account {

    private final int UID;
    private final LocalDate createdAt;

    private final String email;
    private final String password;
    private final String fullName;
    private final LocalDate dateOfBirth;
    private final String phoneNumber;

    private final Map<String, Finance> financeProducts;

    protected Account(
            int uid,
            LocalDate createdAt,
            String email,
            String password,
            String fullName,
            LocalDate dateOfBirth,
            String phoneNumber,
            Map<String, Finance> financeProducts
    ) {
        this.UID = uid;
        this.createdAt = createdAt;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.financeProducts = financeProducts;
    }

    protected Account(
            String email,
            String password,
            String fullName,
            LocalDate dateOfBirth,
            String phoneNumber,
            Map<String, Finance> financeProducts
    ) {
        this(generateUID(), LocalDate.now(), email, password, fullName, dateOfBirth, phoneNumber, financeProducts);
    }

    private static int generateUID() {
        return new Random().nextInt(900_000) + 100_000;
    }

    public int getUID() {
        return UID;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Read-only view of finance products owned by this account.
     */
    public Map<String, Finance> getFinanceProducts() {
        return Collections.unmodifiableMap(financeProducts);
    }

    /**
     * Simple credential check.
     * WARNING: passwords must be hashed in real systems.
     */
    public boolean login(String email, String password) {
        return this.email.equals(email) && this.password.equals(password);
    }

    @Override
    public String toString() {
        return String.format(
                "Account[UID=%d, fullName=%s, email=%s, createdAt=%s]",
                UID, fullName, email, createdAt
        );
    }

    public void attachFinance(Map<String, Finance> finance) {
    }
}
