package com.github.novabank.domain.finance;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import com.github.novabank.domain.finance.finance_accounts.Finance;
import com.github.novabank.domain.finance.finance_accounts.FinanceType;

public interface FinanceRepository {
    void save(String accountUid, FinanceType type, Finance finance);
    Optional<Finance> find(String accountUid, FinanceType type);
    boolean exists(String accountUid, FinanceType type);
}
