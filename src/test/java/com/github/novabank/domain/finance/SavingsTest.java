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



@DisplayName("Savings Class Test")
class SavingsTest{
    private Chequing validCheq;
    private Savings validSavings;



    @BeforeEach
    void setup(){
        //int UID,  int balance, int dailyWithdrawalLimit, int dailyPurchaseLimit, int dailyTransferLimit
        validCheq = new Chequing(1000, 0, 1000, 1000, 1000);
        validSavings = new Savings(0, 1000, 0, 1000, 1000);
    }

    @Test
    @DisplayName("Should Create a Cheq with valid data stored")
    void testvalidSavingsCreation(){
        assertNotNull(validSavings);
        assertEquals(1000, validSavings.getBalance());
        assertEquals(0, validSavings.getAmountSpentToday());
        assertEquals(1000, validSavings.getDailyWithdrawalLimit());
        assertEquals(1000, validSavings.getDailyPurchaseLimit());
        assertEquals(1000, validSavings.getDailyTransferLimit());
    }

    

    @Test
    @DisplayName("Chequing Account after Interest should remain 0")
    void InterestIfBalIsZero(){
        validSavings.withdraw(1000);
        validSavings.interest();
        assertEquals(validSavings.getBalance(), 0);
    }

    @Test
    @DisplayName("Interest should only be used once even when called twice")
    void RepeatingInterest(){
        int initial = validSavings.getBalance();
        validSavings.interest();
        validSavings.interest();
        assertEquals(validSavings.getBalance(), initial * (1 +validSavings.getInterestRate()));
    }

    @Test
    @DisplayName("Should reject negative AmountSpentToday")
    void testNegativeAmountSpentToday(){
        assertThrows(IllegalArgumentException.class, () -> validSavings.setAmountSpentToday(-1));
    }

    @Test
    @DisplayName("Should reject negative dailyWithdrawalLimit")
    void testNegativeDailyWithdrawalLimit(){
        assertThrows(IllegalArgumentException.class, () -> validSavings.setDailyWithdrawalLimit(-1));
    }
    
    @Test
    @DisplayName("Should reject negative Balance")
    void testNegativeBalance(){
        assertThrows(IllegalArgumentException.class, () -> validSavings.setBalance(-1));
    }
    @Test
    @DisplayName("Should reject negative DailyPurchaseLimit")
    void testNegativeDailyPurchaseLimit(){
        assertThrows(IllegalArgumentException.class, () -> validSavings.setDailyPurchaseLimit(-1));
    }
    @Test
    @DisplayName("Should reject negative DailyTransferLimit")
    void negativeWithdraw(){
        assertThrows(IllegalArgumentException.class, () -> validSavings.withdraw(-1));
    }
    @Test
    @DisplayName("Should reject negative deposit")
    void testNegativedeposit(){
        assertThrows(IllegalArgumentException.class, () -> validSavings.deposit(-1));
    }

    @Test
    @DisplayName("Savings Interest Should apply twice")
    void MonlthyInterest(){
        int initial = validSavings.getBalance();
        validSavings.interest();
        
        LocalDate nextMonth = LocalDate.now().plusMonths(1);
        ZoneId timeZone = ZoneId.of("UTC");
        Instant Instant = nextMonth.atStartOfDay(timeZone).toInstant();

        Clock fixedClock = Clock.fixed(Instant, timeZone);

        nextMonth = LocalDate.now(fixedClock);
        validSavings.interest();
        assertEquals(validSavings.getBalance(), (initial * (1 + validSavings.getInterestRate()) ) * (1 + validSavings.getInterestRate()) );
    }

    @Test
    @DisplayName("Testing Withdraw Validity")
    void testWithdraw(){
        validSavings.withdraw(1);
        assertEquals(999, validSavings.getBalance());
    }
    @Test
    @DisplayName("Testing deposit Validity")
    void testDeposit(){
        validSavings.deposit(1);
        assertEquals(1, validSavings.getBalance());
    }
    @Test
    @DisplayName("Withdrawing more than balance")
    void OverWithdrawing(){
        assertThrows(IllegalArgumentException.class, () -> validSavings.withdraw(1111));
    }

    
}