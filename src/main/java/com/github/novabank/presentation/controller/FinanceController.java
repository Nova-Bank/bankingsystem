package com.github.novabank.presentation.controller;

import com.github.novabank.application.dtos.CreditApplicationResult;
import com.github.novabank.application.dtos.PaymentResult;
import com.github.novabank.application.services.financial_actions.PaymentApplicationService;
import com.github.novabank.infrastructure.CachedSearchService;
import com.github.novabank.infrastructure.SearchTransactions;
import com.github.novabank.presentation.dtos.PaymentRequest;
import com.github.novabank.presentation.response.ApiError;
import com.github.novabank.utils.LogFactory;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class FinanceController {

    private static final Logger log = LogFactory.getLogger(FinanceController.class);

    private final SearchTransactions searchTransactions;
    private final CachedSearchService cache;
    private final PaymentApplicationService paymentApplicationService;

    public FinanceController(SearchTransactions searchTransactions,
                             CachedSearchService cache, PaymentApplicationService paymentApplicationService) {
        this.searchTransactions = searchTransactions;
        this.cache = cache;
        this.paymentApplicationService = paymentApplicationService;
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

    @PostMapping("/pay-credit")
    public ResponseEntity<?> payment(@Valid @RequestBody PaymentRequest dto) {
        log.info("Payment request from={} to={} amount={} type={}",
                dto.getPaymentFrom(), dto.getPaymentTo(), dto.getAmount(), dto.getPaymentType());

        try {
            PaymentResult result = paymentApplicationService.handle(dto);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error("Unexpected error during payment: {}", e.getMessage(), e);
            return new ResponseEntity<>(new ApiError("SERVER_ERROR", "Unexpected system error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
