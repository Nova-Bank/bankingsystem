package com.github.novabank.domain.account;


import com.github.novabank.domain.finance.Finance;

/**
 * Builder interface for creating Account instances 
 * Provi
 * @param <t> the type Account being built
 * @author Josef Geshelin
 * @version 1.0
 * @since 2025-11-14
 */

public interface AccountBuilder <T extends Account>{

    AccountBuilder<T> addFinanceProduct(Finance financeProduct);

    T build();

    ValidationResult validate();

    void reset();
    
}
