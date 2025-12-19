package com.github.novabank.domain.finance.finance_creation;

import com.github.novabank.domain.finance.finance_accounts.Chequing;
import com.github.novabank.domain.finance.finance_accounts.Credit;
import com.github.novabank.domain.finance.finance_accounts.Savings;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FinanceFactoryTest {

    // ---------------- CHEQUING ----------------

    @Test
    void createChequing_premium_returnsChequingWithCorrectLimits() {
        Chequing chequing = FinanceFactory.createChequing("premium");

        assertNotNull(chequing);
        assertEquals(0, chequing.getBalance());
        assertEquals(3000000, chequing.getDailyPurchaseLimit());
        assertEquals(999900, chequing.getDailyWithdrawalLimit());
        assertEquals(2000000, chequing.getDailyTransferLimit());
    }

    @Test
    void createChequing_base_returnsChequingWithCorrectLimits() {
        Chequing chequing = FinanceFactory.createChequing("base");

        assertNotNull(chequing);
        assertEquals(0, chequing.getBalance());
        assertEquals(500000, chequing.getDailyPurchaseLimit());
        assertEquals(300000, chequing.getDailyWithdrawalLimit());
        assertEquals(300000, chequing.getDailyTransferLimit());
    }

    @Test
    void createChequing_invalidType_throwsException() {
        IllegalStateException ex = assertThrows(
                IllegalStateException.class,
                () -> FinanceFactory.createChequing("gold")
        );
        assertEquals("Unknown chequing type: gold", ex.getMessage());
    }

    // ---------------- SAVINGS ----------------

    @Test
    void createSavings_premium_returnsSavingsWithCorrectInterestRate() {
        Savings savings = FinanceFactory.createSavings("premium");

        assertNotNull(savings);
        assertEquals(0.03, savings.getInterestRate());
    }

    @Test
    void createSavings_base_returnsSavingsWithCorrectInterestRate() {
        Savings savings = FinanceFactory.createSavings("base");

        assertNotNull(savings);
        assertEquals(0.02, savings.getInterestRate());
    }

    @Test
    void createSavings_invalidType_throwsException() {
        IllegalStateException ex = assertThrows(
                IllegalStateException.class,
                () -> FinanceFactory.createSavings("gold")
        );
        assertEquals("Unknown savings type: gold", ex.getMessage());
    }

    // ---------------- CREDIT ----------------

//    @Test
//    void createCredit_black_returnsCreditWithCorrectLimit() {
//        Credit credit = FinanceFactory.createCredit("black");
//
//        assertNotNull(credit);
//        assertEquals(100000000, credit.getMaximumBalanceWithoutInterest());
//    }

//    @Test
//    void createCredit_red_returnsCreditWithCorrectLimit() {
//        Credit credit = FinanceFactory.createCredit("red");
//
//        assertNotNull(credit);
//        assertEquals(10000000, credit.getMaximumBalanceWithoutInterest());
//    }

//    @Test
//    void createCredit_silver_returnsCreditWithCorrectLimit() {
//        Credit credit = FinanceFactory.createCredit("silver");
//
//        assertNotNull(credit);
//        assertEquals(1000000, credit.getMaximumBalanceWithoutInterest());
//    }

//    @Test
//    void createCredit_green_returnsCreditWithCorrectLimit() {
//        Credit credit = FinanceFactory.createCredit("green");
//
//        assertNotNull(credit);
//        assertEquals(300000, credit.getMaximumBalanceWithoutInterest());
//    }

    @Test
    void createCredit_invalidType_throwsException() {
        IllegalStateException ex = assertThrows(
                IllegalStateException.class,
                () -> FinanceFactory.createCredit("blue")
        );
        assertEquals("Unknown credit type: blue", ex.getMessage());
    }
}
