package com.github.novabank.domain.finance;

import java.util.Optional;

import com.github.novabank.domain.finance.finance_accounts.Finance;
import com.github.novabank.domain.finance.finance_accounts.FinanceType;

public interface FinanceRepository {
    void save(String accountUid, FinanceType type, Finance finance);
    Optional<Finance> find(String accountUid, FinanceType type);
    boolean exists(String accountUid, FinanceType type);
}
