package com.github.novabank.infrastructure.database;

import com.github.novabank.domain.account.AccountRepository;
import com.github.novabank.domain.account.accounts.Account;
import com.github.novabank.domain.account.accounts.AdultAccount;
import com.github.novabank.domain.account.accounts.ChildAccount;
import com.github.novabank.domain.account.account_creation.AdultAccountBuilder;
import com.github.novabank.domain.account.account_creation.ChildAccountBuilder;
import com.github.novabank.infrastructure.config.FirebaseConfig;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

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
            accountData.put("password", account.getPassword()); // Storing password for login functionality
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
        if ("uid".equals(field) || "dateAdded".equals(field) || "parent".equals(field)) {
            throw new IllegalArgumentException("Invalid field for update: " + field);
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

    @Override
    public Account findByEmail(String email) throws IOException, ExecutionException, InterruptedException {
        FirebaseConfig firebaseConfig = new FirebaseConfig();
        firebaseConfig.initialize();
        Firestore db = firebaseConfig.getFirestore();

        CollectionReference accounts = db.collection("accounts");
        Query query = accounts.whereEqualTo("email", email);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();
        if (documents.isEmpty()) {
            return null;
        }
        return documentToAccount(documents.get(0));
    }

    @Override
    public Account findByPhoneNumber(String phoneNumber) throws IOException, ExecutionException, InterruptedException {
        FirebaseConfig firebaseConfig = new FirebaseConfig();
        firebaseConfig.initialize();
        Firestore db = firebaseConfig.getFirestore();

        CollectionReference accounts = db.collection("accounts");
        Query query = accounts.whereEqualTo("phoneNumber", phoneNumber);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();
        if (documents.isEmpty()) {
            return null;
        }
        return documentToAccount(documents.get(0));
    }

    @Override
    public List<Account> findAll() {
        return List.of();
    }

    private Account documentToAccount(QueryDocumentSnapshot document) {
        Map<String, Object> data = document.getData();
        boolean isChild = (boolean) data.getOrDefault("child", false);

        if (isChild) {
            return new ChildAccountBuilder()
                    .setFullName((String) data.get("fullname"))
                    .setEmail((String) data.get("email"))
                    .setDateOfBirth(LocalDate.parse((String) data.get("dob")))
                    .setPhoneNumber((String) data.get("phoneNumber"))
                    .setPassword((String) data.get("password"))
                    .build();
        } else {
            return new AdultAccountBuilder()
                    .setFullName((String) data.get("fullname"))
                    .setEmail((String) data.get("email"))
                    .setDateOfBirth(LocalDate.parse((String) data.get("dob")))
                    .setPhoneNumber((String) data.get("phoneNumber"))
                    .setPassword((String) data.get("password"))
                    .build();
        }
    }


    @Override
    public Account get(int accountId) throws IOException, ExecutionException, InterruptedException {
        FirebaseConfig firebaseConfig = new FirebaseConfig();
        firebaseConfig.initialize();
        Firestore db = firebaseConfig.getFirestore();

        CollectionReference accounts = db.collection("accounts");
        Query query = accounts.whereEqualTo("UID", accountId);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();
        if (documents.isEmpty()) {
            return null;
        }
        return documentToAccount(documents.get(0));
    }
}
