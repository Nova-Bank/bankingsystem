package com.github.novabank.domain.account.account_creation;

import com.github.novabank.domain.account.accounts.*;
import com.github.novabank.domain.finance.finance_accounts.Finance;
import com.github.novabank.infrastructure.database.AccountRepositoryimpl;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.concurrent.ExecutionException;

/** NOT OFFCIAL DOCUMENTAION. Replace once completed
 * AccountFactory is a DOMAIN layer utility for creating Account objects.
 * Runtime flow when registering a new account:
 *      Application Service -> Factory -> Builder -> Account Constructor -> Domain Object -> Repository -> DB
 * 
 * Instructions for the team:
 * - Use this class whenever you need to create a new Account.
 * - Wrap Builder in Factory for validation
 * - Keeps object construction consistent and safe.
 * - Factory does NOT save to the database. Use AccountRepository.save(account) for that.
 * - Infrastructure does not need to call this. Only the domain/business logic should use the factory.
 *
 * Example usage:
 *   Account newAccount = AccountFactory.create("John Doe", initialBalance);
 */
// Note: Let me know if u want Lombok

// by definition, factory decide which object to create
public class AccountFactory {

    public static Account createAccount(AccountInfo info, List<Finance> financeProducts, AdultAccount parent) throws IOException, InterruptedException, ExecutionException {
        LocalDate today = LocalDate.now();
        int age = Period.between(info.getDateOfBirth(), today).getYears();

        if (age < 18 || parent != null) {
            // Create ChildAccount
            if (parent == null) {
                throw new IllegalArgumentException("Child account must have a parent.");
            }
            ChildAccountBuilder builder = new ChildAccountBuilder()
                    .setFullName(info.getFullName())
                    .setEmail(info.getEmail())
                    .setPassword(info.getPassword())
                    .setDateOfBirth(info.getDateOfBirth())
                    .setPhoneNumber(info.getPhoneNumber());

            for (Finance product : financeProducts) {
                builder.addFinanceProduct(product);
            }

            ChildAccount childAccount = builder.build();
            parent.addChild(childAccount);

            AccountRepositoryimpl impl = new AccountRepositoryimpl();
            impl.create(childAccount);
            System.out.println("\n" + impl.read(childAccount) + "\n");
            return childAccount;

        } else {
            // Create AdultAccount
            AdultAccountBuilder builder = new AdultAccountBuilder()
                    .setFullName(info.getFullName())
                    .setEmail(info.getEmail())
                    .setPassword(info.getPassword())
                    .setDateOfBirth(info.getDateOfBirth())
                    .setPhoneNumber(info.getPhoneNumber());

            for (Finance product : financeProducts) {
                builder.addFinanceProduct(product);
            }

            AdultAccount adultAccount = builder.build();

            AccountRepositoryimpl impl = new AccountRepositoryimpl();
            impl.create(adultAccount);
            System.out.println("\n" + impl.read(adultAccount) + "\n");
            return adultAccount;
        }
    }
}
