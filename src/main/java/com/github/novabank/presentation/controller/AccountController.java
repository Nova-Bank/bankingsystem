package com.github.novabank.presentation.controller;

import com.github.novabank.application.dtos.AccountHistoryDTO;
import com.github.novabank.infrastructure.RecentTransactions;
import com.github.novabank.infrastructure.SearchTransactions;
import com.github.novabank.utils.LogFactory;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private static final Logger log = LogFactory.getLogger(AccountController.class);

    private final SearchTransactions searchTransactions;

    public AccountController(SearchTransactions searchTransactions) {
        this.searchTransactions = searchTransactions;
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<String> getAccount(@PathVariable String accountId) {
        log.info("Fetching account details for accountId={}", accountId);
        return ResponseEntity.ok("Account details for " + accountId);
    }

    @GetMapping("/{accountId}/transactions")
    public ResponseEntity<RecentTransactions> getRecentTransactions(@PathVariable String accountId) {
        log.info("Fetching recent transactions for accountId={}", accountId);
        return ResponseEntity.ok(searchTransactions.search(accountId));
    }

    @PostMapping("/history")
    public ResponseEntity<String> getAccountHistory(@Valid @RequestBody AccountHistoryDTO dto) {
        log.info("Account history request for username={}", dto.getUsername());
        return ResponseEntity.ok("History for " + dto.getUsername());
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transferFunds(@Valid @RequestBody TransferDTO dto) {
        log.info("Transfer request username={} from={} to={} amount={}",
                dto.getUsername(), dto.getFromAccount(), dto.getToAccount(), dto.getAmount());
        return ResponseEntity.ok("Transfer of " + dto.getAmount() + " initiated");
    }
}
