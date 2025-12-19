package com.github.novabank.presentation.controller;

import com.github.novabank.application.dtos.CreditApplicationResult;
import com.github.novabank.infrastructure.CachedSearchService;
import com.github.novabank.infrastructure.SearchTransactions;
import com.github.novabank.presentation.dtos.PaymentRequest;
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

    @PostMapping("/credit/apply")
    public ResponseEntity<String> applyForCredit(@Valid @RequestBody CreditApplicationResult dto) {
        log.info("Credit application user={} type={} limit={} cardType={}",
                dto.getUsername(), dto.getApplicationType(), dto.getRequestedLimit(), dto.getCardType());

        return ResponseEntity.ok("Credit application submitted");
    }

    @PostMapping("/payment")
    public ResponseEntity<String> payment(@Valid @RequestBody PaymentRequest dto) {
        log.info("Payment request from={} to={} amount={} type={}",
                dto.getPaymentFrom(), dto.getPaymentTo(), dto.getAmount(), dto.getPaymentType());

        return ResponseEntity.ok("Payment processed");
    }
}
