package com.github.novabank.controller;

import com.github.novabank.domain.account.AccountInfo;
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

    @PostMapping("/adult")
    public ResponseEntity<String> createAdultAccount(@RequestBody AccountInfo accountInfo) {
        try {
            String accountId = accountService.createAdultAccount(accountInfo);
            return ResponseEntity.ok("Adult account created successfully with ID: " + accountId);
        } catch (ExecutionException | InterruptedException e) {
            Thread.currentThread().interrupt();
            return ResponseEntity.status(500).body("Error creating adult account: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body("Invalid adult account data: " + e.getMessage());
        }
    }
}
