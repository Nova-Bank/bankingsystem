package com.github.novabank.domain.account.account_creation;


import com.github.novabank.domain.account.accounts.Account;
import com.github.novabank.domain.finance.finance_accounts.Finance;

import java.time.LocalDate;

/**
 * Builder interface for creating Account instances 
 * Provi
 * @param <t> the type Account being built
 * @author Josef Geshelin
 * @version 1.0
 * @since 2025-11-14
 */

public interface AccountBuilder <T extends Account>{

    AccountBuilder<T> setUID(int uid);

    AccountBuilder<T> setCreatedAt(LocalDate createdAt);

    AccountBuilder<T> setEmail(String email);

    AccountBuilder<T> setPassword(String password);

    AccountBuilder<T> setFullName(String fullName);

    AccountBuilder<T> setDateOfBirth(LocalDate dateOfBirth);

    AccountBuilder<T> setPhoneNumber(String phoneNumber);

    AccountBuilder<T> addFinanceProduct(Finance financeProduct);

    T build();

    ValidationResult validate();

    void reset();
    
}
