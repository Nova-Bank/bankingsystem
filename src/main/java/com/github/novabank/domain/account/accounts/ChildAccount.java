package com.github.novabank.domain.account.accounts;
import com.github.novabank.domain.finance.finance_accounts.Finance;

import java.time.LocalDate;
import java.util.Map;

/**
 * Child account - must be under 18.
 * Must be linked to an adult account.
 */
public class ChildAccount extends Account {

    private AdultAccount parent;

    public ChildAccount(int uid, LocalDate createdAt, String email, String password, String fullName, LocalDate dateOfBirth, String phoneNumber, Map<String, Finance> financeProducts) {
        super(uid, createdAt, email, password, fullName, dateOfBirth, phoneNumber, financeProducts);
    }

    public ChildAccount(String email, String password, String fullName, LocalDate dateOfBirth, String phoneNumber, Map<String, Finance> financeProducts) {
        super(email, password, fullName, dateOfBirth, phoneNumber, financeProducts);
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