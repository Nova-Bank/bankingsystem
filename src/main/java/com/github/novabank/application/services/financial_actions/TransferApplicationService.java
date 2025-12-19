package com.github.novabank.application.services.financial_actions;

import com.github.novabank.application.dtos.TransferResult;
import com.github.novabank.application.financal_actions.TransferUseCase;
import com.github.novabank.domain.account.AccountRepository;
import com.github.novabank.domain.account.accounts.Account;
import com.github.novabank.domain.finance.finance_accounts.Finance;
import com.github.novabank.domain.finance.finance_accounts.FinanceType;
import com.github.novabank.presentation.dtos.TransferRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Application service wrapping the transfer use case.
 * Accepts TransferRequest from the front-end and returns TransferResult DTO.
 */
@Service
public class TransferApplicationService {

    private final TransferUseCase transferUseCase;
    private final AccountRepository accountRepository;

    public TransferApplicationService(TransferUseCase transferUseCase, AccountRepository accountRepository) {
        this.transferUseCase = transferUseCase;
        this.accountRepository = accountRepository;
    }

    public void transferBetweenOwnAccounts(String username, String fromAccount, String toAccount, double amount) throws Exception {
        Account account = accountRepository.findByEmail(username);
        if (account == null) {
            throw new IllegalStateException("Account not found for email: " + username);
        }

        String fromAccountKey = fromAccount.toLowerCase();
        String toAccountKey = toAccount.toLowerCase();

        Finance from = account.getFinanceProducts().get(fromAccountKey);
        Finance to = account.getFinanceProducts().get(toAccountKey);

        if (from == null) {
            throw new IllegalStateException("Source account not found: " + fromAccount);
        }
        if (to == null) {
            throw new IllegalStateException("Destination account not found: " + toAccount);
        }

        int amountInCents = (int) (amount * 100);

        from.transfer(from, to, amountInCents, LocalDate.now());

        // Update balances in Firestore
        accountRepository.update(account, "accounts." + fromAccountKey + ".balance", from.getBalance());
        accountRepository.update(account, "accounts." + toAccountKey + ".balance", to.getBalance());
    }

    public TransferResult execute(TransferRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("TransferRequest cannot be null");
        }

        // Convert front-end request IDs into domain identifiers if needed
        // Here we assume sourceAccountId / targetAccountId can be used as UIDs
        String fromUid = String.valueOf(request.getSourceAccountId());
        String toUid = String.valueOf(request.getTargetAccountId());

        // Default FinanceType; adjust if you want dynamic types
        FinanceType fromType = FinanceType.CHEQUING;
        FinanceType toType = FinanceType.CHEQUING;

        boolean success = transferUseCase.execute(
                fromUid,
                fromType,
                toUid,
                toType,
                request.getAmount(),
                LocalDate.now()
        );

        // Map domain result -> TransferResult DTO
        TransferResult result = new TransferResult();
        result.setSourceAccountId(request.getSourceAccountId());
        result.setTargetAccountId(request.getTargetAccountId());
        result.setAmount(request.getAmount());
        result.setCurrency(request.getCurrency() != null ? request.getCurrency() : "CAD");
        result.setTimestamp(LocalDateTime.now());
        result.setStatus(success ? "COMPLETED" : "FAILED");

        return result;
    }
}
