import com.github.novabank.FirebaseConfig;
import com.github.novabank.domain.account.Account;
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


        AdultAccount account = new AdultAccountBuilder()
            .setEmail("weeevan6@gmail.com")
            .setDateOfBirth(LocalDate.parse("2006-10-30"))
            .setFullName("Evan Wee")
            .setPassword("Melons123!")
            .setPhoneNumber("6475691030").build();

        ChildAccount child = new ChildAccountBuilder()
            .setFullName("Toufic Joe")
            .setDateOfBirth(LocalDate.parse("2010-10-30"))
            .setPassword("thisisapassword$")
            .setPhoneNumber("6475691030")
            .setemail("thisisaemail@gmail.com").build();

        account.addChild(child);
        uploadAccountToFirestore(account);
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

            db.collection("accounts").document(String.valueOf(account.getUID())).set(accountData).get();
            System.out.println("Account uploaded to Firestore with UID: " + account.getUID());
        } catch (Exception e) {
            System.err.println("Error uploading account to Firestore: " + e.getMessage());
            e.printStackTrace();
        }
    }
}