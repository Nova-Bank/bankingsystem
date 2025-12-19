package com.github.novabank.presentation.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.novabank.application.dtos.LoginResult;
import com.github.novabank.application.services.customer_accounts.LoginApplicationService;
import com.github.novabank.application.services.customer_accounts.RegisterApplicationService;
import com.github.novabank.application.services.financial_actions.GetBalancesApplicationService;
import com.github.novabank.application.services.financial_actions.TransferApplicationService;
import com.github.novabank.presentation.dtos.FrontendTransferRequest;
import com.github.novabank.presentation.dtos.LoginRequest;
import com.github.novabank.presentation.dtos.RegisterRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class CustomerAccountController {

    private final RegisterApplicationService registerApplicationService;
    private final LoginApplicationService loginApplicationService;
    private final GetBalancesApplicationService getBalancesApplicationService;
    private final TransferApplicationService transferApplicationService;

    public CustomerAccountController(RegisterApplicationService registerApplicationService,
                                     LoginApplicationService loginApplicationService,
                                     GetBalancesApplicationService getBalancesApplicationService,
                                     TransferApplicationService transferApplicationService) {
        this.registerApplicationService = registerApplicationService;
        this.loginApplicationService = loginApplicationService;
        this.getBalancesApplicationService = getBalancesApplicationService;
        this.transferApplicationService = transferApplicationService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            registerApplicationService.execute(request);
            return ResponseEntity.ok(new GenericResponse(true, "Registration successful"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new GenericResponse(false, "Registration failed: " + e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            LoginResult loginResult = loginApplicationService.execute(request);
            if (loginResult == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new GenericResponse(false, "Invalid credentials"));
            }
            Map<String, Double> balances = getBalancesApplicationService.execute(request.getEmail());
            return ResponseEntity.ok(new LoginSuccessResponse(true, balances));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new GenericResponse(false, "Server error: " + e.getMessage()));
        }
    }

    @GetMapping("/get-balances")
    public ResponseEntity<?> getBalances(@RequestParam String username) {
        try {
            Map<String, Double> balances = getBalancesApplicationService.execute(username);
            if (balances == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(new LoginSuccessResponse(true, balances));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new GenericResponse(false, "Server error: " + e.getMessage()));
        }
    }

    @PostMapping("/transfer")
    public ResponseEntity<?> transfer(@RequestBody FrontendTransferRequest request) {
        try {
            transferApplicationService.transferBetweenOwnAccounts(
                    request.getUsername(),
                    request.getFromAccount(),
                    request.getToAccount(),
                    request.getAmount()
            );
            Map<String, Double> balances = getBalancesApplicationService.execute(request.getUsername());
            return ResponseEntity.ok(new LoginSuccessResponse(true, balances));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new GenericResponse(false, "Transfer failed: " + e.getMessage()));
        }
    }

    // --- Response DTOs ---

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private static class GenericResponse {
        public boolean success;
        public String message;

        public GenericResponse(boolean success, String message) {
            this.success = success;
            this.message = message;
        }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private static class LoginSuccessResponse {
        public boolean success;
        public Map<String, Double> balances;

        public LoginSuccessResponse(boolean success, Map<String, Double> balances) {
            this.success = success;
            this.balances = balances;
        }
    }
}
