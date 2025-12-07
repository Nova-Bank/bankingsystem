import com.github.novabank.FirebaseConfig;
import com.github.novabank.domain.account.Account;
import com.github.novabank.domain.account.AccountFactory;
import com.github.novabank.domain.account.AccountInfo;
import com.google.cloud.firestore.Firestore;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.github.novabank.domain.account.AdultAccount;
import com.github.novabank.domain.account.AdultAccountBuilder;
import com.github.novabank.domain.account.ChildAccount;
import com.github.novabank.domain.account.ChildAccountBuilder;

public class Main {
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        System.out.println("I exist");

        AccountInfo adultinfo = new AccountInfo("weeevan6@gmail.com", "Melons123!", "Evan Wee", LocalDate.parse("2006-10-30"), "6475691030");
        AdultAccount adult = new AccountFactory().createAdultAccount(adultinfo);

        // AdultAccount account = new AdultAccountBuilder()
        //     .setEmail("weeevan6@gmail.com")
        //     .setDateOfBirth(LocalDate.parse("2006-10-30"))
        //     .setFullName("Evan Wee")
        //     .setPassword("Melons123!")
        //     .setPhoneNumber("6475691030").build();

        // ChildAccount child = new ChildAccountBuilder()
        //     .setFullName("Toufic Joe")
        //     .setDateOfBirth(LocalDate.parse("2010-10-30"))
        //     .setPassword("thisisapassword$")
        //     .setPhoneNumber("6475691030")
        //     .setEmail("thisisaemail@gmail.com").build();

        AccountInfo childinfo = new AccountInfo("thisisaemail@gmail.com", "thisisapassword$", "Toufic Joe", LocalDate.parse("2010-10-30"), "6475691030");
        ChildAccount child = new AccountFactory().createChildAccount(childinfo, adult);

        // account.addChild(child);
        uploadAccountToFirestore(adult);
        uploadAccountToFirestore(child);
    }

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
}