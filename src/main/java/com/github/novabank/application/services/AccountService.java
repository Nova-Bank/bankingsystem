package com.github.novabank.application.services;

import com.github.novabank.domain.account.AccountRepository;
import com.github.novabank.domain.account.accounts.Account;
import com.github.novabank.domain.finance.FinanceRepository;
import com.github.novabank.domain.finance.finance_accounts.Finance;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Service
public class AccountService {

    private final AccountRepository accountRepo;
    private final FinanceRepository financeRepo;

    public AccountService(AccountRepository accountRepo,
                          FinanceRepository financeRepo) {
        this.accountRepo = accountRepo;
        this.financeRepo = financeRepo;
    }

    public void deposit(int accountId, String productKey, int amount) throws IOException, ExecutionException, InterruptedException {
        Account account = accountRepo.findById(accountId);
        Finance finance = account.getFinanceProducts().get(productKey);

        finance.deposit(amount);

        financeRepo.save(accountId, productKey, finance);
    }
}
