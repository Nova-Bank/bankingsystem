package com.github.novabank.domain.finance.finance_creation;

import com.github.novabank.domain.finance.finance_accounts.Credit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreditBuilderTest {

    private CreditBuilder builder;

    @BeforeEach
    void setUp() {
        builder = new CreditBuilder();
    }

    // ---------------- BUILD (HAPPY PATH) ----------------

    @Test
    void build_withValidValues_createsCreditAccount() {
        Credit credit = builder
                .setCreditLimit(5000)
                .setCreditInterestRate(0.21)
                .setBalance(1000)
                .setAmountSpentToday(200)
                .setDailyWithdrawalLimit(500)
                .setDailyPurchaseLimit(500)
                .setDailyTransferLimit(500)
                .setDailySpendingLimit(1000)
                .build();

        assertNotNull(credit);
        assertEquals(1000, credit.getBalance());
    }

    // ---------------- VALIDATION FAILURES ----------------

    @Test
    void build_negativeBalance_throwsException() {
        builder
                .setCreditLimit(1000)
                .setCreditInterestRate(0.2)
                .setBalance(-1)
                .setAmountSpentToday(0)
                .setDailyWithdrawalLimit(100)
                .setDailyPurchaseLimit(100)
                .setDailyTransferLimit(100)
                .setDailySpendingLimit(100);

        IllegalStateException ex =
                assertThrows(IllegalStateException.class, builder::build);

        assertEquals("balance less than 0", ex.getMessage());
    }

    @Test
    void build_zeroCreditLimit_throwsException() {
        builder
                .setCreditLimit(0)
                .setCreditInterestRate(0.2)
                .setBalance(0)
                .setAmountSpentToday(0)
                .setDailyWithdrawalLimit(100)
                .setDailyPurchaseLimit(100)
                .setDailyTransferLimit(100)
                .setDailySpendingLimit(100);

        assertThrows(IllegalStateException.class, builder::build);
    }

    @Test
    void build_negativeInterestRate_throwsException() {
        builder
                .setCreditLimit(1000)
                .setCreditInterestRate(-0.1)
                .setBalance(0)
                .setAmountSpentToday(0)
                .setDailyWithdrawalLimit(100)
                .setDailyPurchaseLimit(100)
                .setDailyTransferLimit(100)
                .setDailySpendingLimit(100);

        assertThrows(IllegalStateException.class, builder::build);
    }

    @Test
    void build_negativeAmountSpentToday_throwsException() {
        builder
                .setCreditLimit(1000)
                .setCreditInterestRate(0.2)
                .setBalance(0)
                .setAmountSpentToday(-10)
                .setDailyWithdrawalLimit(100)
                .setDailyPurchaseLimit(100)
                .setDailyTransferLimit(100)
                .setDailySpendingLimit(100);

        assertThrows(IllegalStateException.class, builder::build);
    }

    @Test
    void build_zeroDailyLimits_throwException() {
        builder
                .setCreditLimit(1000)
                .setCreditInterestRate(0.2)
                .setBalance(0)
                .setAmountSpentToday(0)
                .setDailyWithdrawalLimit(0)
                .setDailyPurchaseLimit(0)
                .setDailyTransferLimit(0)
                .setDailySpendingLimit(0);

        assertThrows(IllegalStateException.class, builder::build);
    }

    // ---------------- RESET ----------------

    @Test
    void reset_clearsAllFields_andCausesValidationFailure() {
        builder
                .setCreditLimit(1000)
                .setCreditInterestRate(0.2)
                .setBalance(500)
                .setAmountSpentToday(50)
                .setDailyWithdrawalLimit(100)
                .setDailyPurchaseLimit(100)
                .setDailyTransferLimit(100)
                .setDailySpendingLimit(100);

        builder.reset();

        assertThrows(IllegalStateException.class, builder::build,
                "After reset(), builder should be invalid");
    }

    // ---------------- FLUENT API ----------------

    @Test
    void setters_returnSameBuilderInstance() {
        CreditBuilder returned = builder
                .setCreditLimit(1000)
                .setCreditInterestRate(0.2)
                .setBalance(100)
                .setAmountSpentToday(0)
                .setDailyWithdrawalLimit(100)
                .setDailyPurchaseLimit(100)
                .setDailyTransferLimit(100)
                .setDailySpendingLimit(100);

        assertSame(builder, returned);
    }

    // ---------------- TO STRING ----------------

    @Test
    void toString_containsAllKeyFields() {
        builder
                .setCreditLimit(2000)
                .setCreditInterestRate(0.1999)
                .setBalance(300)
                .setAmountSpentToday(20)
                .setDailyWithdrawalLimit(100)
                .setDailyPurchaseLimit(200)
                .setDailyTransferLimit(300)
                .setDailySpendingLimit(400);

        String result = builder.toString();

        assertTrue(result.contains("creditLimit=2000"));
        assertTrue(result.contains("creditInterestRate=0.1999"));
        assertTrue(result.contains("balance=300"));
        assertTrue(result.contains("amountSpentToday=20"));
        assertTrue(result.contains("dailyWithdrawalLimit=100"));
        assertTrue(result.contains("dailyPurchaseLimit=200"));
        assertTrue(result.contains("dailyTransferLimit=300"));
        assertTrue(result.contains("dailySpendingLimit=400"));
    }
}
