package com.github.novabank.presentation.controller;

import com.github.novabank.presentation.dtos.CreditApplicationDTO;
import com.github.novabank.presentation.dtos.PaymentDTO;
import com.github.novabank.infrastructure.CachedSearchService;
import com.github.novabank.infrastructure.RecentTransactions;
import com.github.novabank.infrastructure.SearchTransactions;
import com.github.novabank.utils.LogFactory;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/finance")
public class FinanceController {

    private static final Logger log = LogFactory.getLogger(FinanceController.class);

    private final SearchTransactions searchTransactions;
    private final CachedSearchService cache;

    public FinanceController(SearchTransactions searchTransactions,
                             CachedSearchService cache) {
        this.searchTransactions = searchTransactions;
        this.cache = cache;
    }

    @GetMapping("/summary")
    public ResponseEntity<String> summary() {
        log.info("Fetching financial summary");
        return ResponseEntity.ok("Financial summary");
    }

    @GetMapping("/search")
    public ResponseEntity<RecentTransactions> search(@RequestParam String query) {
        log.info("Searching transactions with query={}", query);

        Object cachedResult = cache.get(query);
        if (cachedResult != null) {
            log.info("Returning cached search result");
            return ResponseEntity.ok((RecentTransactions) cachedResult);
        }

        RecentTransactions result = searchTransactions.search(query);
        cache.put(query, result);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/credit/apply")
    public ResponseEntity<String> applyForCredit(@Valid @RequestBody CreditApplicationResult dto) {
        log.info("Credit application user={} type={} limit={} cardType={}",
                dto.getUsername(), dto.getApplicationType(), dto.getRequestedLimit(), dto.getCardType());
        return ResponseEntity.ok("Credit application submitted");
    }

    @PostMapping("/payment")
    public ResponseEntity<String> payment(@Valid @RequestBody PaymentDTO dto) {
        log.info("Payment request username={} from={} to={} amount={} type={}",
                dto.getUsername(), dto.getPaymentFrom(), dto.getPaymentTo(), dto.getAmount(), dto.getPaymentType());
        return ResponseEntity.ok("Payment processed");
    }
}
