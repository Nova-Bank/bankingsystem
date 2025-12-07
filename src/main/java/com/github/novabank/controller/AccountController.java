package com.github.novabank.controller;

import com.github.novabank.domain.account.Account;
import com.github.novabank.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<String> createAccount(@RequestBody Account account) {
        try {
            String accountId = accountService.createAccount(account);
            return ResponseEntity.ok("Account created successfully with ID: " + accountId);
        } catch (ExecutionException | InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore interruption status
            return ResponseEntity.status(500).body("Error creating account: " + e.getMessage());
        }
    }
}
