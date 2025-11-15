package com.github.novabank.builder;

import com.github.novabank.model.Account;

/**
 * Builder interface for creating Account instances 
 * Provi
 * @param <t> the type Account being built
 * @author Josef Geshelin
 * @version 1.0
 * @since 2025-11-14
 */

public interface AccountBuilder <T extends Account>{

    T build();

    void validate();

    void reset();
    
}