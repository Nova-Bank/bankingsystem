package com.github.novabank.presentation.controller;

import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.github.novabank.infrastructure.CachedSearchService;
import com.github.novabank.infrastructure.RecentTransactions;
import com.github.novabank.infrastructure.SearchTransactions;
import com.github.novabank.utils.LogFactory;

/**
 * REST controller responsible for finance-related API endpoints.
 *
 * Responsibilities:
 * - Expose financial data endpoints
 * - Orchestrate search and summary requests
 * - Apply caching at the API level
 * - Log API interactions
 *
 */
@RestController
@RequestMapping("/api/finance")
public class FinanceController {

    private static final Logger log =
            LogFactory.getLogger(FinanceController.class);

    private final SearchTransactions searchTransactions;
    private final CachedSearchService cache;

    /**
     * Constructs the FinanceController.
     *
     * @param searchTransactions service used to search transactions
     * @param cache caching service for repeated searches
     */
    public FinanceController(
            SearchTransactions searchTransactions,
            CachedSearchService cache) {
        this.searchTransactions = searchTransactions;
        this.cache = cache;
    }

    /**
     * Retrieves a financial summary.
     *
     * @return placeholder financial summary
     */
    @GetMapping("/summary")
    public ResponseEntity<String> summary() {

        log.info("Fetching financial summary");

        // Placeholder until finance service is integrated
        return ResponseEntity.ok("Financial summary");
    }

    /**
     * Searches transactions based on a query string.
     * Results may be returned from cache if available.
     *
     * @param query search criteria
     * @return matching transactions
     */
    @GetMapping("/search")
    public ResponseEntity<RecentTransactions> search(
            @RequestParam String query) {

        log.info("Searching transactions with query={}", query);

        Object cachedResult = cache.get(query);
        if (cachedResult != null) {
            log.info("Returning cached search result");
            return ResponseEntity.ok((RecentTransactions) cachedResult);
        }

        RecentTransactions result =
                searchTransactions.search(query);

        cache.put(query, result);
        return ResponseEntity.ok(result);
    }
}
