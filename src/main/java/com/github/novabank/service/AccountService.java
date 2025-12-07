package com.github.novabank.service;

import com.github.novabank.domain.account.AccountInfo;
import com.github.novabank.domain.account.AdultAccount;
import com.github.novabank.domain.account.ChildAccount;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;

import java.util.concurrent.ExecutionException;

public class AccountService {

    private final Firestore firestore;

    public AccountService(Firestore firestore) {
        this.firestore = firestore;
    }

    public String createAdultAccount(AccountInfo accountInfo) throws ExecutionException, InterruptedException {
        AdultAccount account = AdultAccount.create(accountInfo);

        ApiFuture<DocumentReference> future = firestore.collection("accounts").add(account);
        DocumentReference documentReference = future.get();
        System.out.println("Added adult account with ID: " + documentReference.getId());
        return documentReference.getId();
    }

    public String createChildAccount(AccountInfo childInfo, String parentAccountId) throws ExecutionException, InterruptedException {
        AdultAccount parentAccount = getAccount(parentAccountId);
        if (parentAccount == null) {
            throw new IllegalArgumentException("Parent account with ID " + parentAccountId + " not found.");
        }

        ChildAccount childAccount = ChildAccount.create(childInfo, parentAccount);

        ApiFuture<DocumentReference> childFuture = firestore.collection("accounts").add(childAccount);
        DocumentReference childDocumentReference = childFuture.get();
        System.out.println("Added child account with ID: " + childDocumentReference.getId());

        updateAccount(parentAccountId, parentAccount);
        System.out.println("Updated parent account " + parentAccountId + " with new child.");

        return childDocumentReference.getId();
    }

    public AdultAccount getAccount(String documentId) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firestore.collection("accounts").document(documentId);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        if (document.exists()) {
            return document.toObject(AdultAccount.class);
        } else {
            System.out.println("No such document!");
            return null;
        }
    }

    public void updateAccount(String documentId, AdultAccount account) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> future = firestore.collection("accounts").document(documentId).set(account);
        System.out.println("Update time : " + future.get().getUpdateTime());
    }

    public void deleteAccount(String documentId) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> future = firestore.collection("accounts").document(documentId).delete();
        System.out.println("Delete time : " + future.get().getUpdateTime());
    }
}
