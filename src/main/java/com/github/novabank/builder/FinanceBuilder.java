package com.github.novabank.builder;

import com.github.novabank.model.Finance;

public interface FinanceBuilder<T extends Finance>{


    T build();

    void validate();

    void reset();
}