package com.github.novabank.presentation.controller;

import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.github.novabank.infrastructure.RecentTransactions;
import com.github.novabank.infrastructure.SearchTransactions;
import com.github.novabank.utils.LogFactory;

/**
 * REST controller responsible for account-related API endpoints.
 *
 * Responsibilities:
 * - Expose account-facing REST endpoints
 * - Orchestrate request flow
 * - Delegate logic to service-layer abstractions
 * - Log incoming requests
 *
 */
@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private static final Logger log =
            LogFactory.getLogger(AccountController.class);

    private final SearchTransactions searchTransactions;

    /**
     * Constructs the AccountController.
     *
     * @param searchTransactions service used to retrieve transaction data
     */
    public AccountController(SearchTransactions searchTransactions) {
        this.searchTransactions = searchTransactions;
    }

    /**
     * Retrieves account information for a given account ID.
     *
     * @param accountId unique identifier of the account
     * @return placeholder account information response
     */
    @GetMapping("/{accountId}")
    public ResponseEntity<String> getAccount(
            @PathVariable String accountId) {

        log.info("Fetching account details for accountId={}", accountId);

        // Placeholder until service/domain integration
        return ResponseEntity.ok("Account details for " + accountId);
    }

    /**
     * Retrieves recent transactions associated with an account.
     *
     * @param accountId unique identifier of the account
     * @return recent transactions for the account
     */
    @GetMapping("/{accountId}/transactions")
    public ResponseEntity<RecentTransactions> getRecentTransactions(
            @PathVariable String accountId) {

        log.info("Fetching recent transactions for accountId={}", accountId);

        RecentTransactions transactions =
                searchTransactions.search(accountId);

        return ResponseEntity.ok(transactions);
    }
}
