package com.github.novabank.domain.finance.finance_accounts;

import com.github.novabank.domain.finance.finance_accounts.Credit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CreditTest {

    private Credit credit;

    @BeforeEach
    void setUp() {
        credit = new Credit(
                1000,  // creditLimit
                0.21,  // interest rate
                0,     // getBalance()
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
        assertEquals(200, credit.getBalance());
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
    // ---------------- INTEREST ----------------

    @Test
    void interest_firstCall_doesNothing() {
        credit.purchase(500);
        credit.interest();
        assertEquals(500, credit.getBalance());
    }

    @Test
    void interest_secondCall_sameMonth_zeroesgetBalance() {
        credit.purchase(500);
        credit.interest(); // sets lastMonth
        credit.interest(); // same month

        assertEquals(500, credit.getBalance());
    }

   
}
