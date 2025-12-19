package com.github.novabank.presentation.controller;

import com.github.novabank.application.dtos.AccountHistoryResult;
import com.github.novabank.application.dtos.TransferDTO;
import com.github.novabank.application.dtos.TransferResult;
import com.github.novabank.application.services.financial_actions.GetBalancesApplicationService;
import com.github.novabank.application.services.financial_actions.TransferApplicationService;
import com.github.novabank.infrastructure.RecentTransactions;
import com.github.novabank.infrastructure.SearchTransactions;
import com.github.novabank.presentation.dtos.TransferRequest;
import com.github.novabank.presentation.response.ApiError;
import com.github.novabank.utils.LogFactory;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AccountController {

    private static final Logger log = LogFactory.getLogger(AccountController.class);

    private final SearchTransactions searchTransactions;
    private final GetBalancesApplicationService getBalancesApplicationService;
    private final TransferApplicationService transferApplicationService;

    public AccountController(SearchTransactions searchTransactions, GetBalancesApplicationService getBalancesApplicationService, TransferApplicationService transferApplicationService) {
        this.searchTransactions = searchTransactions;
        this.getBalancesApplicationService = getBalancesApplicationService;
        this.transferApplicationService = transferApplicationService;
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
    public ResponseEntity<String> getAccountHistory(@Valid @RequestBody AccountHistoryResult dto) {
        log.info("Account history request for username={}", dto.getUsername());
        return ResponseEntity.ok("History for " + dto.getUsername());
    }

    @PostMapping("/transfer")
    public ResponseEntity<?> transferFunds(@Valid @RequestBody TransferDTO dto) {
        log.info("Transfer request username={} from={} to={} amount={}",
                dto.getUsername(), dto.getFromAccount(), dto.getToAccount(), dto.getAmount());

        try {
            // The DTOs are a bit messy. For now, we assume the from and to accounts are UIDs.
            // A proper implementation would have a more robust way to identify accounts.
            int fromAccountId = Integer.parseInt(dto.getFromAccount());
            int toAccountId = Integer.parseInt(dto.getToAccount());

            TransferRequest request = new TransferRequest(fromAccountId, toAccountId, dto.getAmount(), "CAD", LocalDateTime.now(), "PENDING");
            TransferResult result = transferApplicationService.execute(request);

            if ("FAILED".equals(result.getStatus())) {
                return new ResponseEntity<>(new ApiError("TRANSFER_FAILED", "Transfer failed"), HttpStatus.BAD_REQUEST);
            }

            return ResponseEntity.ok(result);
        } catch (NumberFormatException e) {
            log.error("Invalid account ID format: {}", e.getMessage());
            return new ResponseEntity<>(new ApiError("INVALID_INPUT", "Account IDs must be numeric"), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Unexpected error during transfer: {}", e.getMessage(), e);
            return new ResponseEntity<>(new ApiError("SERVER_ERROR", "Unexpected system error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-balances")
    public ResponseEntity<?> getBalances() {
        log.info("Fetching balances");
        try {
            // FIXME: Hardcoded user email. This should be retrieved from the security context
            // after implementing session management/authentication.
            String userEmail = "test@example.com";
            Map<String, Double> balances = getBalancesApplicationService.execute(userEmail);

            if (balances == null) {
                return new ResponseEntity<>(new ApiError("USER_NOT_FOUND", "User not found"), HttpStatus.NOT_FOUND);
            }

            return ResponseEntity.ok(balances);
        } catch (Exception e) {
            log.error("Unexpected error while fetching balances: {}", e.getMessage(), e);
            return new ResponseEntity<>(new ApiError("SERVER_ERROR", "Unexpected system error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
