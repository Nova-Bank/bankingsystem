package com.github.novabank.domain.finance;

public interface FinanceBuilder<T extends Finance>{


    Finance build();

    void validate();

    void reset();

    public String toString();
}