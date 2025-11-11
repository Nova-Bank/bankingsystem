package main.java.com.github.novabank.model;

import java.time.LocalDate;
import java.util.Random;

/**
 * Abstract base class for all account types.
 * Immutable after creation - no setters for critical fields.
 */
public abstract class Account {

    private final int UID;
    private final String email;
    private final String password;
    private final String fullName;
    private final LocalDate dateOfBirth;
    private final String phoneNumber;

    protected Account(AccountInfo info) {
        Random random = new Random();
        this.UID = random.nextInt(900000) + 100000; // 6-digit random number
        this.email = info.getEmail();
        this.password = info.getPassword();
        this.fullName = info.getFullName();
        this.dateOfBirth = info.getDateOfBirth();
        this.phoneNumber = info.getPhoneNumber();
    }

    public int getUID() {
        return this.UID;
    }

    public String getEmail() {
        return this.email;
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


    /**
     * Verify login credentials.
     * @param email Email to verify
     * @param password Password to verify
     * @return true if credentials match
     */
    public boolean login(String email, String password) {
        return this.email.equals(email) && this.password.equals(password);
    }
}

