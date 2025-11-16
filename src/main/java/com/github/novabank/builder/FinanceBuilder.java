package com.github.novabank.builder;

import com.github.novabank.model.Chequing;
import com.github.novabank.model.Finance;

public interface FinanceBuilder<T extends Finance>{


    Chequing build();

    void validate();

    void reset();

    public String toString();
}