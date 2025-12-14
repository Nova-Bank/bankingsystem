package com.github.novabank.domain.account;

import com.github.novabank.domain.finance.Finance;
import com.github.novabank.infrastructure.database.AccountRepositoryimpl;

import java.io.IOException;
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

//TODO: Combind createAdultAccount & createChildAccount
// by definition, factory decide which object to create
public class AccountFactory {

    /**
     * Creates an adult account using the provided account information.
     *
     * @param info The account information for the adult account.
     * @return A new AdultAccount instance.
     * @throws ExecutionException 
     * @throws InterruptedException 
     * @throws IOException 
     * @throws IllegalArgumentException if the account information is invalid.
     */
    public static AdultAccount createAdultAccount(AccountInfo info, List<Finance> financeProducts) throws IOException, InterruptedException, ExecutionException {

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

        // RETURNS MAP<String, Object>
        System.out.println("\n" + impl.read(adultAccount) + "\n");

        return adultAccount;

    }

    /**
     * Creates a child account and links it to a parent account.
     *
     * @param info   The account information for the child account.
     * @param parent The parent adult account.
     * @return A new ChildAccount instance.
     * @throws ExecutionException 
     * @throws InterruptedException 
     * @throws IOException 
     * @throws IllegalArgumentException if the account information is invalid or the parent is null.
     */
    public static ChildAccount createChildAccount(AccountInfo info, AdultAccount parent, List<Finance> financeProducts) throws IOException, InterruptedException, ExecutionException {
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

        // RETURNS MAP<String, Object>
        System.out.println("\n" + impl.read(childAccount) + "\n");

        return childAccount;
    }
}
