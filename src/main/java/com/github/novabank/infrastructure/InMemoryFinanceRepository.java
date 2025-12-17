package com.github.novabank.infrastructure;

import com.github.novabank.domain.finance.FinanceRepository;
import com.github.novabank.domain.finance.finance_accounts.Finance;
import com.github.novabank.domain.finance.finance_creation.FinanceType;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryFinanceRepository implements FinanceRepository {

        private final Map<String, EnumMap<FinanceType, Finance>> store = new HashMap<>();

        @Override
        public void save(String accountUid, FinanceType type, Finance finance) {
            store.computeIfAbsent(accountUid, k -> new EnumMap<>(FinanceType.class))
                    .put(type, finance);
        }

        @Override
        public Optional<Finance> find(String accountUid, FinanceType type) {
            EnumMap<FinanceType, Finance> map = store.get(accountUid);
            if (map == null) return Optional.empty();
            return Optional.ofNullable(map.get(type));
        }

        @Override
        public boolean exists(String accountUid, FinanceType type) {
            return find(accountUid, type).isPresent();
        }
    }