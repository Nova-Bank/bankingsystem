import com.github.novabank.domain.account.Account;
import com.github.novabank.domain.account.AccountFactory;
import com.github.novabank.domain.account.AccountInfo;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.github.novabank.domain.account.AdultAccount;
import com.github.novabank.domain.account.ChildAccount;
import com.github.novabank.infrastructure.config.FirebaseConfig;

public class Main {
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        System.out.println("I exist");

        AccountInfo adultinfo = new AccountInfo("thisisanemail@gmail.com", "Melons123!", "Evan Wee", LocalDate.parse("2006-10-30"), "6475691030");
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


        FirebaseConfig firebaseConfig = new FirebaseConfig();
        firebaseConfig.initialize();
        Firestore db = firebaseConfig.getFirestore();
        
        String adultEmail = db.collection("accounts").document(Integer.toString(adult.getUID())).get().get().getString("email");
        System.out.println(adultEmail);
    }

    
}