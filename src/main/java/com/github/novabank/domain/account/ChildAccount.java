package com.github.novabank.domain.account;
import java.time.LocalDate;

/**
 * Child account - must be under 18.
 * Must be linked to an adult account.
 */
public class ChildAccount extends Account {

    private AdultAccount parent;

    public ChildAccount(String email, String password, String fullName, LocalDate dateOfBirth, String phoneNumber) {
        super(email, password, fullName, dateOfBirth, phoneNumber);
    }

    /**
     * Set parent account (package-private - only AdultAccount can call this).
     */
    void setParent(AdultAccount parent) {
        this.parent = parent;
    }

    /**
     * Get parent account.
     */
    public AdultAccount getParent() {
        return parent;
    }

    /**
     * Check if child has a parent linked.
     */
    public boolean hasParent() {
        return parent != null;
    }
}