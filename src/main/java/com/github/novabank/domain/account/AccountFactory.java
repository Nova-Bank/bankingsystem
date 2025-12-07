package com.github.novabank.domain.account;

import java.util.HashMap;
import java.util.Map;

import com.github.novabank.FirebaseConfig;
import com.google.cloud.firestore.Firestore;

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

public class AccountFactory {

    public static void uploadAccountToFirestore(Account account) {
        try {
            FirebaseConfig firebaseConfig = new FirebaseConfig();
            firebaseConfig.initialize();
            Firestore db = firebaseConfig.getFirestore();

            Map<String, Object> accountData = new HashMap<>();
            accountData.put("UID", account.getUID());
            accountData.put("email", account.getEmail());
            accountData.put("fullName", account.getFullName());
            accountData.put("dateOfBirth", account.getDateOfBirth().toString());
            accountData.put("phoneNumber", account.getPhoneNumber());
            
            if (account instanceof ChildAccount) {
                accountData.put("child", true);

                /*
                    IF TRUE, ADD FIELD REFERENCE TO ADULT DOCUMENT (firestore database)
                */

            }
            else {
                accountData.put("child", false);
            }

            db.collection("accounts").document(String.valueOf(account.getUID())).set(accountData).get();
            System.out.println("Account uploaded to Firestore with UID: " + account.getUID());
        } catch (Exception e) {
            System.err.println("Error uploading account to Firestore: " + e.getMessage());
            e.printStackTrace();
        }
    }


    /**
     * Creates an adult account using the provided account information.
     *
     * @param info The account information for the adult account.
     * @return A new AdultAccount instance.
     * @throws IllegalArgumentException if the account information is invalid.
     */
    public static AdultAccount createAdultAccount(AccountInfo info) {

        AdultAccount adultAccount = new AdultAccountBuilder()
            .setFullName(info.getFullName())
            .setEmail(info.getEmail())
            .setPassword(info.getPassword())
            .setDateOfBirth(info.getDateOfBirth())
            .setPhoneNumber(info.getPhoneNumber())
            .build();

        uploadAccountToFirestore(adultAccount);

        return adultAccount;
    }

    /**
     * Creates a child account and links it to a parent account.
     *
     * @param info   The account information for the child account.
     * @param parent The parent adult account.
     * @return A new ChildAccount instance.
     * @throws IllegalArgumentException if the account information is invalid or the parent is null.
     */
    public static ChildAccount createChildAccount(AccountInfo info, AdultAccount parent) {
        if (parent == null) {
            throw new IllegalArgumentException("Child account must have a parent.");
        }

        ChildAccount childAccount = new ChildAccountBuilder()
                .setFullName(info.getFullName())
                .setEmail(info.getEmail())
                .setPassword(info.getPassword())
                .setDateOfBirth(info.getDateOfBirth())
                .setPhoneNumber(info.getPhoneNumber())
                .build();

        parent.addChild(childAccount);

        uploadAccountToFirestore(childAccount);

        return childAccount;
    }
}

