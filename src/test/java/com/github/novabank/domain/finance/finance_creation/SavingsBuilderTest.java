package com.github.novabank.domain.finance.finance_creation;

import com.github.novabank.domain.finance.finance_accounts.Savings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SavingsBuilderTest {

    private SavingsBuilder builder;

    @BeforeEach
    void setUp() {
        builder = new SavingsBuilder();
    }

    // ---------------- BUILD (HAPPY PATH) ----------------

    @Test
    void build_withValidValues_createsSavingsAccount() {
        Savings savings = builder
                .setBalance(1000)
                .setDailyWithdrawalLimit(500)
                .setDailyPurchaseLimit(400)
                .setDailyTransferLimit(300)
                .setInterestRate(0.03)
                .build();

        assertNotNull(savings);
        assertEquals(1000, savings.getBalance());
        assertEquals(500, savings.getDailyWithdrawalLimit());
        assertEquals(400, savings.getDailyPurchaseLimit());
        assertEquals(300, savings.getDailyTransferLimit());
        assertEquals(0.03, savings.getInterestRate());
    }

    // ---------------- VALIDATION FAILURES ----------------

    @Test
    void build_negativeBalance_throwsException() {
        builder
                .setBalance(-1)
                .setDailyWithdrawalLimit(100)
                .setDailyPurchaseLimit(100)
                .setDailyTransferLimit(100)
                .setInterestRate(0.01);

        IllegalStateException ex = assertThrows(
                IllegalStateException.class,
                builder::build
        );
        assertEquals("balance less than 0", ex.getMessage());
    }

    @Test
    void build_zeroDailyWithdrawalLimit_throwsException() {
        builder
                .setBalance(0)
                .setDailyWithdrawalLimit(0)
                .setDailyPurchaseLimit(100)
                .setDailyTransferLimit(100)
                .setInterestRate(0.01);

        assertThrows(IllegalStateException.class, builder::build);
    }

    @Test
    void build_zeroDailyPurchaseLimit_throwsException() {
        builder
                .setBalance(0)
                .setDailyWithdrawalLimit(100)
                .setDailyPurchaseLimit(0)
                .setDailyTransferLimit(100)
                .setInterestRate(0.01);

        assertThrows(IllegalStateException.class, builder::build);
    }

    @Test
    void build_zeroDailyTransferLimit_throwsException() {
        builder
                .setBalance(0)
                .setDailyWithdrawalLimit(100)
                .setDailyPurchaseLimit(100)
                .setDailyTransferLimit(0)
                .setInterestRate(0.01);

        assertThrows(IllegalStateException.class, builder::build);
    }

    @Test
    void build_zeroInterestRate_throwsException() {
        builder
                .setBalance(0)
                .setDailyWithdrawalLimit(100)
                .setDailyPurchaseLimit(100)
                .setDailyTransferLimit(100)
                .setInterestRate(0.0);

        assertThrows(IllegalStateException.class, builder::build);
    }

    // ---------------- RESET ----------------

    @Test
    void reset_clearsAllFields_andCausesValidationFailure() {
        builder
                .setBalance(100)
                .setDailyWithdrawalLimit(100)
                .setDailyPurchaseLimit(100)
                .setDailyTransferLimit(100)
                .setInterestRate(0.01);

        builder.reset();

        // interestRate is NOT reset! So this test may still pass incorrectly
        assertThrows(IllegalStateException.class, builder::build,
                "After reset(), builder should be invalid");
    }

    // ---------------- FLUENT API ----------------

    @Test
    void setters_returnSameBuilderInstance() {
        SavingsBuilder returned = builder
                .setBalance(100)
                .setDailyWithdrawalLimit(100)
                .setDailyPurchaseLimit(100)
                .setDailyTransferLimit(100)
                .setInterestRate(0.02);

        assertSame(builder, returned);
    }

    // ---------------- TO STRING ----------------

    @Test
    void toString_containsKeyFields() {
        builder
                .setBalance(50)
                .setDailyWithdrawalLimit(10)
                .setDailyPurchaseLimit(20)
                .setDailyTransferLimit(30)
                .setInterestRate(0.01);

        String result = builder.toString();

        assertTrue(result.contains("balance=50"));
        assertTrue(result.contains("dailyWithdrawlLimit=10"));
        assertTrue(result.contains("dailyPurchaseLimits=20"));
        assertTrue(result.contains("dailyTransferLimit=30"));
        // interestRate is missing in toString → test exposes bug
        assertFalse(result.contains("0.01"));
    }
}
