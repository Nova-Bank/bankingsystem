package com.github.novabank.domain.finance;

import com.github.novabank.domain.finance.finance_accounts.Credit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CreditTest {

    private Credit credit;

    @BeforeEach
    void setUp() {
        credit = new Credit(
                0,     // creditBalance (unused?)
                1000,  // creditLimit
                0.21,  // interest rate
                0,     // balance
                500,   // dailyWithdrawalLimit
                500,   // dailyPurchaseLimit
                500,   // dailyTransferLimit
                500    // dailySpendingLimit
        );
    }

    // ---------------- PURCHASE ----------------

    @Test
    void purchase_validAmount_increasesBalance() {
        credit.purchase(200);
        assertEquals(200, credit.balance);
    }

    @Test
    void purchase_negativeAmount_throwsException() {
        assertThrows(IllegalStateException.class, () ->
                credit.purchase(-50)
        );
    }

    @Test
    void purchase_exceedsCreditLimit_throwsException() {
        credit.purchase(900);
        assertThrows(IllegalStateException.class, () ->
                credit.purchase(200)
        );
    }

    // ---------------- CLOSE BALANCE ----------------

    @Test
    void closeBalance_reducesBalance() {
        credit.purchase(300);
        credit.closeBalance(100);
        assertEquals(200, credit.balance);
    }

    @Test
    void closeBalance_canGoNegative_currentBehavior() {
        credit.purchase(100);
        credit.closeBalance(200);
        assertEquals(-100, credit.balance);
    }

    // ---------------- MINIMUM PAYMENT ----------------

    @Test
    void minimumPayment_aboveTenPercent_returnsOnePercent() {
        credit.purchase(2000);
        double min = credit.getMinimumPayment();
        assertEquals(20.0, min);
    }

    @Test
    void minimumPayment_belowTen_returnsTenOrBalance() {
        credit.purchase(500);
        assertEquals(10.0, credit.getMinimumPayment());

        credit.closeBalance(495);
        assertEquals(5.0, credit.getMinimumPayment());
    }

    // ---------------- INTEREST ----------------

    @Test
    void interest_firstCall_doesNothing() {
        credit.purchase(500);
        credit.interest();
        assertEquals(500, credit.balance);
    }

    @Test
    void interest_secondCall_sameMonth_zeroesBalance_bug() {
        credit.purchase(500);
        credit.interest(); // sets lastMonth
        credit.interest(); // same month

        assertEquals(0, credit.balance,
                "BUG: interest() resets balance to 0");
    }

    // ---------------- LATE FEE ----------------

    @Test
    void addLateFee_addsFeeEvenIfInterestBroken() {
        credit.purchase(300);
        credit.addLateFee();
        assertEquals(325, credit.balance);
    }
}
