package com.github.novabank.application;

import com.github.novabank.domain.finance.finance_accounts.Finance;
import com.github.novabank.domain.finance.FinanceRepository;
import com.github.novabank.domain.finance.finance_accounts.FinanceType;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
public class FirebaseFinanceRepository implements FinanceRepository {

    @Override
    public void save(String accountUid, FinanceType type, Finance finance) {

    }

    @Override
    public Optional<Finance> find(String accountUid, FinanceType type) {
        return Optional.empty();
    }

    @Override
    public boolean exists(String accountUid, FinanceType type) {
        return false;
    }

    @Override
    public int getNetPositionCentsForAccount(int accountUid) {
        return 0;
    }

    @Override
    public Map<String, Finance> loadForAccount(int accountId) {
        throw new UnsupportedOperationException("Implement Firebase mapping");
    }

    @Override
    public void save(int accountId, String key, Finance finance) {
        // FirebaseDatabase.getInstance()
        //   .getReference("accounts")
        //   .child(String.valueOf(accountId))
        //   .child("finance")
        //   .child(key)
        //   .setValueAsync(finance);
    }
}

