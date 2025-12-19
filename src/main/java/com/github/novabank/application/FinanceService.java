package com.github.novabank.application;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.github.novabank.domain.finance.FinanceRepository;
import com.github.novabank.domain.finance.finance_accounts.Finance;
import com.github.novabank.domain.finance.finance_accounts.FinanceType;

@Service
public class FinanceService {

    private final FinanceRepository financeRepository;

    public FinanceService(FinanceRepository financeRepository) {
        this.financeRepository = financeRepository;
    }

    private Finance mustGet(String accountUid, FinanceType type) {
        return financeRepository.find(accountUid, type)
                .orElseThrow(() ->
                        new IllegalStateException(
                                "Finance not found for accountUid=" + accountUid + " type=" + type));
    }

    public int deposit(String accountUid, FinanceType type, int amountCents) {
        Finance f = mustGet(accountUid, type);
        f.deposit(amountCents);
        financeRepository.save(accountUid, type, f);
        return f.getBalance();
    }

    public int withdraw(String accountUid, FinanceType type, int amountCents) {
        Finance f = mustGet(accountUid, type);
        f.withdraw(amountCents);
        financeRepository.save(accountUid, type, f);
        return f.getBalance();
    }

    public boolean transfer(String fromAccountUid, FinanceType fromType,
                            String toAccountUid, FinanceType toType,
                            int amountCents, LocalDate date) {

        Finance from = mustGet(fromAccountUid, fromType);
        Finance to = mustGet(toAccountUid, toType);

        boolean ok = from.transfer(from, to, amountCents, date);

        financeRepository.save(fromAccountUid, fromType, from);
        financeRepository.save(toAccountUid, toType, to);

        return ok;
    }

    public int getBalance(String accountUid, FinanceType type) {
        return mustGet(accountUid, type).getBalance();
    }

    public void applyInterest(String accountUid, FinanceType type) {
        Finance f = mustGet(accountUid, type);
        f.interest();
        financeRepository.save(accountUid, type, f);
    }

    public Finance getFinance(String accountUid, FinanceType type) {
        return mustGet(accountUid, type);
    }
}
