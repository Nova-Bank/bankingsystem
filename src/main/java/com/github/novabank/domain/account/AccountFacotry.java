package com.github.novabank.domain.account;

/**
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

public class AccountFacotry {
    
}
