package com.github.novabank.model;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Cheqing Class Test")
class CheqingTest{
    private Chequing validCheq;


    @BeforeEach
    void setup(){
        // int balance, int amountSpentToday, int dailyWithdrawalLimit, int dailyPurchaseLimit, int dailyTransferLimit, int dailySpendingLimit
        validCheq = new Chequing(1000, 0, 1000, 1000, 1000, 1000);

    }

    @Test
    @Order(1)
    @DisplayName("Should Create a Cheq with valid data stored")
    void testValidCheqCreation(){
        assertNotNull(validCheq);
        assertEquals(1000, validCheq.getBalance());
        assertEquals(0, validCheq.getAmountSpentToday());
        assertEquals(1000, validCheq.getDailyWithdrawalLimit());
        assertEquals(1000, validCheq.getDailyPurchaseLimit());
        assertEquals(1000, validCheq.getDailyTransferLimit());
        assertEquals(1000, validCheq.getDailySpendingLimit());
    }

     @Test
    @Order(1)
    @DisplayName("Chequing Interest should increase Balance")

    void testInterest(){
        validCheq.interest();
        assertEquals(validCheq.getBalance(), 1000 * validCheq.getInterestRate());
    }

    /*
     * Cases 1: Usual Case & ensure it stores data correctly
     * Case 2: Ensure Interest increases balance by the correct amount
     * Case 3: Interest if Balance = 0 remains 0
     * Case 4: Attempts to do Interest twice in the same month
     * Case 5: setAmountSpentToday, setDailyTransferLimit, setDailySpendingLimit, setBalance, setDailyWithdrawalLimit, setDailyPurchaseLimit, withdraw, deposit( -12903821)
     * Case 6: withdraw in the future
     * Case 7: withdraw before bank was made
     * Case 8: withdraw Usual Case
     * Case 9: withdraw with amount > balance
     * Case 10: Deposit absurdly high number (should raise error)
     * 
     * Case 11: transfer Usual Case
     * Case 12: sender doesnt have enough money
     * Case 13: Transfer before bank was made
     * Case 14: Transfer in the future
     * Case 15: 
     * 
     */
}