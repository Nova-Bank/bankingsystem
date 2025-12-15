package com.github.novabank.domain.finance;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import com.github.novabank.domain.finance.finance_accounts.Finance;

public interface FinanceRepository {

    Finance save(Finance account);

    Optional<Finance> findByUid(int uid);

    // Read all accounts
    List<Finance> findAll();

    boolean existsByUid(int uid);

    void deleteByUid(int uid);

    List<Finance> findBy(Predicate<Finance> predicate);
}