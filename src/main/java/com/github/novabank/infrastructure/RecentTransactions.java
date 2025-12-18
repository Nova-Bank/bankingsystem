package com.github.novabank.infrastructure;

import java.util.List;

/**
 * Represents recent transactions returned to the API.
 */
public class RecentTransactions {

    private final List<String> transactions;

    public RecentTransactions(List<String> transactions) {
        this.transactions = transactions;
    }

    public List<String> getTransactions() {
        return transactions;
    }
}
