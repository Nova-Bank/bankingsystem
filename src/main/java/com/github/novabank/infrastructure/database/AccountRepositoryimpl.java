package com.github.novabank.infrastructure.database;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.github.novabank.domain.account.AccountRepository;
import com.github.novabank.domain.account.accounts.*;

import com.github.novabank.infrastructure.config.FirebaseConfig;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.FieldValue;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;

public class AccountRepositoryimpl implements AccountRepository {

    @Override
    public void create(Account account) {
        try {
            FirebaseConfig firebaseConfig = new FirebaseConfig();
            firebaseConfig.initialize();
            Firestore db = firebaseConfig.getFirestore();

            Map<String, Object> accountData = new HashMap<>();
            accountData.put("UID", account.getUID());
            accountData.put("email", account.getEmail());
            accountData.put("fullname", account.getFullName());
            accountData.put("dob", account.getDateOfBirth().toString());
            accountData.put("phoneNumber", account.getPhoneNumber());
            accountData.put("dateAdded", FieldValue.serverTimestamp());
            accountData.put("accounts", account.getFinanceProducts());
            
            if (account instanceof ChildAccount) {
                accountData.put("child", true);

                try {
                    AdultAccount childAdult = ((ChildAccount)account).getParent();
                    DocumentReference referencedDoc = db.collection("accounts").document(Integer.toString(childAdult.getUID()));

                    accountData.put("parent", referencedDoc);
                }
                catch (Exception e) {
                    System.err.println("Error uploading account to Firestore: " + e.getMessage());
                    e.printStackTrace();
                }

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

    @Override
    public Map<String, Object> read(Account account) throws InterruptedException, ExecutionException, IOException {
        FirebaseConfig firebaseConfig = new FirebaseConfig();
        firebaseConfig.initialize();
        Firestore db = firebaseConfig.getFirestore();

        Map<String,Object> map = db.collection("accounts").document(Integer.toString(account.getUID())).get().get().getData();
        return map;
    }
    
    @Override
    public void update(Account account, String field, Object value) throws IOException, InterruptedException, ExecutionException {

        if (field == "uid" || field == "dateAdded" || field == "parent") {
            throw new IllegalArgumentException("Invalid field");
        }

        FirebaseConfig firebaseConfig = new FirebaseConfig();
        firebaseConfig.initialize();
        Firestore db = firebaseConfig.getFirestore();       
        
        DocumentReference ref = db.collection("accounts").document(Integer.toString(account.getUID()));     

        ApiFuture<WriteResult> future = ref.update(field, value);

        WriteResult result = future.get();
        System.out.println("Updating field: [" + field + "]\n with value: [" + value + "]\nto account # [" + account.getUID() + "]");
    }


    @Override
    public void delete(Account account) throws InterruptedException, ExecutionException, IOException {
		
		FirebaseConfig firebaseConfig = new FirebaseConfig();
        firebaseConfig.initialize();
        Firestore db = firebaseConfig.getFirestore();       

		db.collection("accounts").document(Integer.toString(account.getUID())).get();

		System.out.println("\nDeleting account # [" + account.getUID() + "]\n");
		ApiFuture<WriteResult> writeResult = db.collection("accounts").document(Integer.toString(account.getUID())).delete();

    }
}
