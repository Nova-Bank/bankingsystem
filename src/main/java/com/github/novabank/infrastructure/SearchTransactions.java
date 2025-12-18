package com.github.novabank.infrastructure;

import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * Handles transaction search requests.
 */
@Service
public class SearchTransactions {

    /**
     * Executes a search.
     *
     * @param query search criteria
     * @return result set
     */
    public RecentTransactions search(String query) {
        return new RecentTransactions(Collections.emptyList());
    }
}
