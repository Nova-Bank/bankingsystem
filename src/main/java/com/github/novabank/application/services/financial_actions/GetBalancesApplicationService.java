package com.github.novabank.application.services.financial_actions;

import com.github.novabank.domain.account.AccountRepository;
import com.github.novabank.domain.account.accounts.Account;
import com.github.novabank.domain.finance.finance_accounts.Chequing;
import com.github.novabank.domain.finance.finance_accounts.Credit;
import com.github.novabank.domain.finance.finance_accounts.Finance;
import com.github.novabank.domain.finance.finance_accounts.Savings;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GetBalancesApplicationService {

    private final AccountRepository accountRepository;

    public GetBalancesApplicationService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Map<String, Double> execute(String email) throws Exception {
        Account account = accountRepository.findByEmail(email);
        if (account == null) {
            // Or throw an exception, depending on desired error handling
            return null;
        }

        Map<String, Double> balances = new HashMap<>();
        balances.put("chequing", 0.0);
        balances.put("savings", 0.0);
        balances.put("credit", 0.0);

        for (Finance financeProduct : account.getFinanceProducts().values()) {
            if (financeProduct instanceof Chequing) {
                balances.put("chequing", financeProduct.getBalance() / 100.0);
            } else if (financeProduct instanceof Savings) {
                balances.put("savings", financeProduct.getBalance() / 100.0);
            } else if (financeProduct instanceof Credit) {
                balances.put("credit", financeProduct.getBalance() / 100.0);
            }
        }

        return balances;
    }
}
