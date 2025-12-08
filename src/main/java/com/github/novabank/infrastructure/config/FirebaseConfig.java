package com.github.novabank.infrastructure.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirebaseConfig {

    private Firestore firestore;

    @PostConstruct
    public void initialize() throws IOException {
        FileInputStream serviceAccount = new FileInputStream("firebase-service-account.json"); // Path to your key

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        if (FirebaseApp.getApps().isEmpty()) { // Ensure only one FirebaseApp is initialized
            FirebaseApp.initializeApp(options);
        }

        this.firestore = FirestoreClient.getFirestore();
        System.out.println("Firebase initialized and Firestore client ready.");
    }

    @Bean
    public Firestore getFirestore() {
        return firestore;
    }
}
