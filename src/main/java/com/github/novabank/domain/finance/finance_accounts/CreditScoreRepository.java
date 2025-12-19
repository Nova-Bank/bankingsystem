package com.github.novabank.domain.finance.finance_accounts;

import java.time.YearMonth;
import java.util.Optional;

public interface CreditScoreRepository {
    boolean existsForMonth(int accountUid, YearMonth month);
    void save(int accountUid, YearMonth month, int score, MonthlySnapshot snapshot);
    Optional<Integer> getScore(int accountUid, YearMonth month);
}