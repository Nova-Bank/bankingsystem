package com.github.novabank.domain.account.account_creation;

import com.github.novabank.domain.account.accounts.*;
import com.github.novabank.domain.finance.Finance;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountFactoryTest {

    private AccountInfo adultInfo() {
        return new AccountInfo(
                "Adult User",
                "adult@test.com",
                "password",
                LocalDate.now().minusYears(25),
                "1234567890"
        );
    }

    private AccountInfo childInfo() {
        return new AccountInfo(
                "Child User",
                "child@test.com",
                "password",
                LocalDate.now().minusYears(10),
                "0987654321"
        );
    }

    @Test
    void createsAdultAccount_whenAgeIs18OrOlder_andNoParent() throws Exception {
        AccountInfo info = adultInfo();

        Account account = AccountFactory.createAccount(
                info,
                List.of(),
                null
        );

        assertNotNull(account);
        assertInstanceOf(AdultAccount.class, account);
        assertEquals("Adult User", account.getFullName());
    }

    @Test
    void createsChildAccount_whenUnder18_andParentProvided() throws Exception {
        AdultAccount parent = new AdultAccountBuilder()
                .setFullName("Parent")
                .setEmail("parent@test.com")
                .setPassword("password")
                .setDateOfBirth(LocalDate.now().minusYears(40))
                .setPhoneNumber("1111111111")
                .build();

        AccountInfo info = childInfo();

        Account account = AccountFactory.createAccount(
                info,
                List.of(),
                parent
        );

        assertNotNull(account);
        assertInstanceOf(ChildAccount.class, account);
        assertTrue(parent.getChildren().contains(account));
    }

    @Test
    void throwsException_whenChildAccountHasNoParent() {
        AccountInfo info = childInfo();

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> AccountFactory.createAccount(info, List.of(), null)
        );

        assertEquals("Child account must have a parent.", ex.getMessage());
    }

    @Test
    void treatsAccountAsChild_whenParentIsProvided_evenIfAgeIsAdult() throws Exception {
        AdultAccount parent = new AdultAccountBuilder()
                .setFullName("Parent")
                .setEmail("parent@test.com")
                .setPassword("password")
                .setDateOfBirth(LocalDate.now().minusYears(45))
                .setPhoneNumber("2222222222")
                .build();

        AccountInfo info = adultInfo();

        Account account = AccountFactory.createAccount(
                info,
                List.of(),
                parent
        );

        assertInstanceOf(ChildAccount.class, account);
    }

    @Test
    void addsFinanceProductsToAccount() throws Exception {
        Finance finance = new Finance(/* mock or real constructor */);

        AccountInfo info = adultInfo();

        Account account = AccountFactory.createAccount(
                info,
                List.of(finance),
                null
        );

        assertEquals(1, account.getFinanceProducts().size());
    }
}
