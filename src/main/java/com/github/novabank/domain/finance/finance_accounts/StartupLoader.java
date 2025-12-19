package com.github.novabank.domain.finance.finance_accounts;

import com.github.novabank.domain.account.AccountRepository;
import com.github.novabank.domain.account.accounts.Account;
import com.github.novabank.domain.finance.FinanceRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class StartupLoader {

    private final AccountRepository accountRepo;
    private final FinanceRepository financeRepo;

    public StartupLoader(AccountRepository accountRepo,
                         FinanceRepository financeRepo) {
        this.accountRepo = accountRepo;
        this.financeRepo = financeRepo;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void load() {
        List<Account> accounts = accountRepo.loadAll();

        for (Account acc : accounts) {
            Map<String, Finance> finance = financeRepo.loadForAccount(acc.getUID());
            acc.attachFinance(finance);
        }
    }
}

