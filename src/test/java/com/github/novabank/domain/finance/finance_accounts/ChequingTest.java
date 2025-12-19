package com.github.novabank.domain.finance.finance_accounts;

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

import com.github.novabank.domain.finance.finance_accounts.Chequing;
import com.github.novabank.domain.finance.finance_accounts.Savings;



@DisplayName("Cheqing Class Test")
class ChequingTest{
    private Chequing validCheq;
    private Savings validSavings;



    @BeforeEach
    void setup(){
        validCheq = new Chequing(1000, 1000, 1000, 1000);
        validSavings = new Savings(0, 1000, 1000, 1000, 0.05);
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
        assertEquals(validCheq.getBalance(), Math.round(initial*(1+validCheq.getInterestRate())));
    }

    @Test
    @DisplayName("Chequing Account after Interest should remain 0")
    void InterestIfBalIsZero(){
        validCheq.withdraw(1000);
        validCheq.withdraw(1000);
        validCheq.interest();
        assertEquals(validCheq.getBalance(), 0);
    }

    @Test
    @DisplayName("Interest should not be applied twice in the same month")
    void RepeatingInterest() {
        int initial = validCheq.getBalance();

        validCheq.interest();
        validCheq.interest();

        assertEquals(initial, validCheq.getBalance());
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
    void MonlthyInterest() {
        ZoneId zone = ZoneId.of("UTC");

        Clock jan = Clock.fixed(LocalDate.of(2025, 1, 1).atStartOfDay(zone).toInstant(), zone);
        Chequing c = new Chequing(1000, 1000, 1000, 1000, jan);

        c.interest(); // initializes month, no interest yet

        Clock feb = Clock.fixed(LocalDate.of(2025, 2, 1).atStartOfDay(zone).toInstant(), zone);
        Chequing cNext = new Chequing(c.getBalance(), 1000, 1000, 1000, feb);
        // or better: add a setClock method, but you said you can’t change method names, not fields

        cNext.interest(); // now month advanced, interest applies
    }



    @Test
    @DisplayName("Withdraw in the future shouldn't be possible")
    void FutureWithdraw(){
        LocalDate nextInterest = LocalDate.now();
        nextInterest.plusMonths(1);

        assertThrows(IllegalArgumentException.class, () -> validCheq.withdraw(1));
    }
    
    @Test
    @DisplayName("Testing deposit Validity")
    void testDeposit(){
        validCheq.deposit(1);
        assertEquals(1001, validCheq.getBalance());
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