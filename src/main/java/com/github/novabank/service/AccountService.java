package com.github.novabank.service;

import java.util.concurrent.ExecutionException;

import com.github.novabank.domain.account.Account;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;

public class AccountService {

    private final Firestore firestore;

    public AccountService(Firestore firestore) {
        this.firestore = firestore;
    }

    public String createAccount(Account account) throws ExecutionException, InterruptedException {
        // Add a new document with a generated ID to the "accounts" collection
        ApiFuture<DocumentReference> future = firestore.collection("accounts").add(account);
        DocumentReference documentReference = future.get();
        System.out.println("Added document with ID: " + documentReference.getId());
        return documentReference.getId();
    }

    public Account getAccount(String documentId) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firestore.collection("accounts").document(documentId);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get(); // Blocks on response
        if (document.exists()) {
            return document.toObject(Account.class);
        } else {
            System.out.println("No such document!");
            return null;
        }
    }

    public void updateAccount(String documentId, Account account) throws ExecutionException, InterruptedException {
        // Update an existing document
        ApiFuture<WriteResult> future = firestore.collection("accounts").document(documentId).set(account);
        System.out.println("Update time : " + future.get().getUpdateTime());
    }

    public void deleteAccount(String documentId) throws ExecutionException, InterruptedException {
        // Delete a document
        ApiFuture<WriteResult> future = firestore.collection("accounts").document(documentId).delete();
        System.out.println("Delete time : " + future.get().getUpdateTime());
    }
}
