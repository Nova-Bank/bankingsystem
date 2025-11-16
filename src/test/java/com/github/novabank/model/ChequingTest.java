package com.github.novabank.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.ZoneId;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@DisplayName("Cheqing Class Test")
class CheqingTest{
    private Chequing validCheq;



    @BeforeEach
    void setup(){
        //int UID,  int balance, int dailyWithdrawalLimit, int dailyPurchaseLimit, int dailyTransferLimit
        validCheq = new Chequing(1, 1000, 1000, 1000, 1000);

    }

    @Test
    @DisplayName("Should Create a Cheq with valid data stored")
    void testValidCheqCreation(){
        assertNotNull(validCheq);
        assertEquals(1000, validCheq.getBalance());
        assertEquals(0, validCheq.getAmountSpentToday());
        assertEquals(1000, validCheq.getDailyWithdrawalLimit());
        assertEquals(1000, validCheq.getDailyPurchaseLimit());
        assertEquals(1000, validCheq.getDailyTransferLimit());
    }

     @Test
    @DisplayName("Chequing Interest should increase Balance")
    void testInterest(){
        validCheq.interest();
        assertEquals(validCheq.getBalance(), 1000 * validCheq.getInterestRate());
    }

    @Test
    @DisplayName("Chequing Account after Interest should remain 0")
    void InterestIfBalIsZero(){
        validCheq.withdraw(1000, null);
        validCheq.interest();
        assertEquals(validCheq.getBalance(), 0);
    }

    @Test
    @DisplayName("Interest should only be used once even when called twice")
    void RepeatingInterest(){
        validCheq.interest();
        validCheq.interest();
        assertEquals(validCheq.getBalance(), 1000 * validCheq.getInterestRate());
    }

    @Test
    @DisplayName("Should reject negative AmountSpentToday")
    void testNegativeAmountSpentToday(){
        assertThrows(IllegalArgumentException.class, validCheq.setAmountSpentToday(-1));
    }

    @Test
    @DisplayName("Should reject negative dailyWithdrawalLimit")
    void testNegativeDailyWithdrawalLimit(){
        assertThrows(IllegalArgumentException.class, validCheq.setDailyWithdrawalLimit(-1));
    }
    @Test
    @DisplayName("Should reject negative DailySpendingLimit")
    void testDailySpendingLimit(){
        assertThrows(IllegalArgumentException.class, validCheq.setDailySpendingLimit(-1));
    }
    @Test
    @DisplayName("Should reject negative Balance")
    void testNegativeBalance(){
        assertThrows(IllegalArgumentException.class, validCheq.setBalance(-1));
    }
    @Test
    @DisplayName("Should reject negative DailyPurchaseLimit")
    void testNegativeDailyPurchaseLimit(){
        assertThrows(IllegalArgumentException.class, validCheq.setDailyPurchaseLimit(-1));
    }
    @Test
    @DisplayName("Should reject negative DailyTransferLimit")
    void testNegativewithdraw(){
        assertThrows(IllegalArgumentException.class, validCheq.withdraw(-1, LocalDate.now(ZoneId.of("America/Toronto"))));
    }
    @Test
    @DisplayName("Should reject negative deposit")
    void testNegativedeposit(){
        assertThrows(IllegalArgumentException.class, validCheq.deposit(-1, LocalDate.now(ZoneId.of("America/Toronto"))));
    }

    @Test
    @DisplayName("Interest should only be used once even when called twice")
    void MonlthyInterest(){
        validCheq.interest();
        validCheq.interest();
        assertEquals(validCheq.getBalance(), 1000 * validCheq.getInterestRate());
    }



    /*
     * Cases 1: Usual Case & ensure it stores data correctly
     * Case 2: Ensure Interest increases balance by the correct amount
     * Case 3: Interest if Balance = 0 remains 0
     * Case 4: Attempts to do Interest twice in the same month
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