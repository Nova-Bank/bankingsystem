package com.github.novabank.domain.finance.finance_accounts;
import com.github.novabank.domain.finance.finance_accounts.CreditScoreRepository;
import com.github.novabank.domain.finance.finance_accounts.MonthlySnapshot;

import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryCreditScoreRepository implements CreditScoreRepository {

    private record Key(int uid, YearMonth month) {}

    private final Map<Key, Integer> scores = new HashMap<>();
    private final Map<Key, MonthlySnapshot> snapshots = new HashMap<>();

    @Override
    public boolean existsForMonth(int accountUid, YearMonth month) {
        return scores.containsKey(new Key(accountUid, month));
    }

    @Override
    public void save(int accountUid, YearMonth month, int score, MonthlySnapshot snapshot) {
        Key key = new Key(accountUid, month);
        scores.put(key, score);
        snapshots.put(key, snapshot);
    }

    @Override
    public Optional<Integer> getScore(int accountUid, YearMonth month) {
        return Optional.ofNullable(scores.get(new Key(accountUid, month)));
    }
}