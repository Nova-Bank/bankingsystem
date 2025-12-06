package com.github.novabank.domain.finance;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;



@DisplayName("Cheqing Class Test")
class ChequingTest{
    private Chequing validCheq;
    private Savings validSavings;



    @BeforeEach
    void setup(){
        //int UID,  int balance, int dailyWithdrawalLimit, int dailyPurchaseLimit, int dailyTransferLimit
        validCheq = new Chequing(1000, 0, 1000, 1000);
        validSavings = new Savings(0, 1000, 0, 1000);
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
        int initial = validCheq.getBalance();
        validCheq.interest();
        assertEquals(validCheq.getBalance(), initial * (1 + validCheq.getInterestRate()));
    }

    @Test
    @DisplayName("Chequing Account after Interest should remain 0")
    void InterestIfBalIsZero(){
        validCheq.withdraw(1000);
        validCheq.interest();
        assertEquals(validCheq.getBalance(), 0);
    }

    @Test
    @DisplayName("Interest should only be used once even when called twice")
    void RepeatingInterest(){
        int initial = validCheq.getBalance();
        validCheq.interest();
        validCheq.interest();
        assertEquals(validCheq.getBalance(), initial * (1 +validCheq.getInterestRate()));
    }

    @Test
    @DisplayName("Should reject negative AmountSpentToday")
    void testNegativeAmountSpentToday(){
        assertThrows(IllegalArgumentException.class, () -> validCheq.setAmountSpentToday(-1));
    }

    @Test
    @DisplayName("Should reject negative dailyWithdrawalLimit")
    void testNegativeDailyWithdrawalLimit(){
        assertThrows(IllegalArgumentException.class, () -> validCheq.setDailyWithdrawalLimit(-1));
    }
    @Test
    @DisplayName("Should reject negative DailySpendingLimit")
    void testDailySpendingLimit(){
        assertThrows(IllegalArgumentException.class, () -> validCheq.setDailySpendingLimit(-1));
    }
    @Test
    @DisplayName("Should reject negative Balance")
    void testNegativeBalance(){
        assertThrows(IllegalArgumentException.class, () -> validCheq.setBalance(-1));
    }
    @Test
    @DisplayName("Should reject negative DailyPurchaseLimit")
    void testNegativeDailyPurchaseLimit(){
        assertThrows(IllegalArgumentException.class, () -> validCheq.setDailyPurchaseLimit(-1));
    }
    @Test
    @DisplayName("Should reject negative DailyTransferLimit")
    void negativeWithdraw(){
        assertThrows(IllegalArgumentException.class, () -> validCheq.withdraw(-1));
    }
    @Test
    @DisplayName("Should reject negative deposit")
    void testNegativedeposit(){
        assertThrows(IllegalArgumentException.class, () -> validCheq.deposit(-1));
    }

    @Test
    @DisplayName("Chequing Interest Should apply twice")
    void MonlthyInterest(){
        int initial = validCheq.getBalance();
        validCheq.interest();
        
        LocalDate nextMonth = LocalDate.now().plusMonths(1);
        ZoneId timeZone = ZoneId.of("UTC");
        Instant Instant = nextMonth.atStartOfDay(timeZone).toInstant();

        Clock fixedClock = Clock.fixed(Instant, timeZone);

        nextMonth = LocalDate.now(fixedClock);
        validCheq.interest();
        assertEquals(validCheq.getBalance(), (initial * (1 + validCheq.getInterestRate()) ) * (1 + validCheq.getInterestRate()) );
    }

    @Test
    @DisplayName("Savings Interest should work correctly")
    void interestSavings(){
        int initial = validCheq.getBalance();
        validSavings.interest();
        assertEquals(validSavings.getBalance(), (initial * (1+ validSavings.getInterestRate())));
    }

    @Test
    @DisplayName("Withdraw in the future shouldn't be possible")
    void FutureWithdraw(){
        LocalDate nextInterest = LocalDate.now();
        nextInterest.plusMonths(1);

        assertThrows(IllegalArgumentException.class, () -> validCheq.withdraw(1));
    }
    @Test
    @DisplayName("deposit in the future shouldn't be possible")
    void FutureDeposit(){
        LocalDate nextInterest = LocalDate.now();
        nextInterest.plusMonths(1);

        assertThrows(IllegalArgumentException.class, () -> validCheq.deposit(1));
    }

    @Test
    @DisplayName("Testing Withdraw Validity")
    void testWithdraw(){
        validCheq.withdraw(1);
        assertEquals(999, validCheq.getBalance());
    }
    @Test
    @DisplayName("Testing deposit Validity")
    void testDeposit(){
        validCheq.deposit(1);
        assertEquals(1, validCheq.getBalance());
    }
    @Test
    @DisplayName("Withdrawing more than balance")
    void OverWithdrawing(){
        assertThrows(IllegalArgumentException.class, () -> validCheq.withdraw(1111));
    }
    @Test
    @DisplayName("Testing Tranfer Validity")
    void testTransfer(){
        validCheq.transfer(validCheq, validSavings , 1, LocalDate.now());
        assertEquals(999, validCheq.getBalance());
        assertEquals(1001, validSavings.getBalance());
    }
    @Test
    @DisplayName("Should reject negative Transfer")
    void negativeTransfer(){
        assertThrows(IllegalArgumentException.class, () -> validCheq.transfer(validCheq, validSavings , -1, LocalDate.now()));
    }
    @Test
    @DisplayName("Should reject Future Transfer")
    void futureTransfer(){
        assertThrows(IllegalArgumentException.class, () -> validCheq.transfer(validCheq, validSavings , 1, LocalDate.now().plusMonths(1)));
    }
    @Test
    @DisplayName("Should reject sender not having enough money")
    void lowBalanceTransfer(){
        assertThrows(IllegalArgumentException.class, () -> validCheq.transfer(validCheq, validSavings , 1001, LocalDate.now()));
    }


    /*
     * Case 5: Interest a year apart
     * Case 7: withdraw before bank was made
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