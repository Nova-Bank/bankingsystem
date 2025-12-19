package com.github.novabank.domain.finance.finance_creation;

import com.github.novabank.domain.finance.finance_accounts.Chequing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChequingBuilderTest {

    private ChequingBuilder builder;

    @BeforeEach
    void setUp() {
        builder = new ChequingBuilder();
    }

    // ---------------- BUILD (HAPPY PATH) ----------------

    @Test
    void build_withValidValues_createsChequingAccount() {
        Chequing chequing = builder
                .setBalance(500)
                .setDailyWithdrawalLimit(300)
                .setDailyPurchaseLimit(400)
                .setDailyTransferLimit(200)
                .build();

        assertNotNull(chequing);
        assertEquals(500, chequing.getBalance());
        assertEquals(300, chequing.getDailyWithdrawalLimit());
        assertEquals(400, chequing.getDailyPurchaseLimit());
        assertEquals(200, chequing.getDailyTransferLimit());
    }

    // ---------------- VALIDATION ----------------

    @Test
    void build_negativeBalance_throwsException() {
        builder
                .setBalance(-1)
                .setDailyWithdrawalLimit(100)
                .setDailyPurchaseLimit(100)
                .setDailyTransferLimit(100);

        IllegalStateException ex = assertThrows(
                IllegalStateException.class,
                builder::build
        );

        assertEquals("balance less than 0", ex.getMessage());
    }

    @Test
    void build_zeroWithdrawalLimit_throwsException() {
        builder
                .setBalance(0)
                .setDailyWithdrawalLimit(0)
                .setDailyPurchaseLimit(100)
                .setDailyTransferLimit(100);

        assertThrows(IllegalStateException.class, builder::build);
    }

    @Test
    void build_negativePurchaseLimit_throwsException() {
        builder
                .setBalance(0)
                .setDailyWithdrawalLimit(100)
                .setDailyPurchaseLimit(-10)
                .setDailyTransferLimit(100);

        assertThrows(IllegalStateException.class, builder::build);
    }

    @Test
    void build_negativeTransferLimit_throwsException() {
        builder
                .setBalance(0)
                .setDailyWithdrawalLimit(100)
                .setDailyPurchaseLimit(100)
                .setDailyTransferLimit(-5);

        assertThrows(IllegalStateException.class, builder::build);
    }

    // ---------------- RESET ----------------

    @Test
    void reset_clearsAllValues_andCausesValidationFailure() {
        builder
                .setBalance(100)
                .setDailyWithdrawalLimit(100)
                .setDailyPurchaseLimit(100)
                .setDailyTransferLimit(100);

        builder.reset();

        assertThrows(IllegalStateException.class, builder::build,
                "After reset(), builder should be invalid");
    }

    // ---------------- BUILDER CHAINING ----------------

    @Test
    void setters_returnSameBuilderInstance() {
        ChequingBuilder returned = builder
                .setBalance(100)
                .setDailyWithdrawalLimit(100)
                .setDailyPurchaseLimit(100)
                .setDailyTransferLimit(100);

        assertSame(builder, returned);
    }

    // ---------------- TO STRING ----------------

    @Test
    void toString_containsKeyFields() {
        builder
                .setBalance(50)
                .setDailyWithdrawalLimit(10)
                .setDailyPurchaseLimit(20)
                .setDailyTransferLimit(30);

        String result = builder.toString();

        assertTrue(result.contains("balance=50"));
        assertTrue(result.contains("dailyWithdrawalLimit=10"));
        assertTrue(result.contains("dailyPurchaseLimits=20"));
        assertTrue(result.contains("dailyTransferLimit=30"));
    }
}
