package com.github.novabank.application;

import com.github.novabank.domain.finance.FinanceRepository;
import com.github.novabank.domain.finance.finance_accounts.*;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.time.Clock;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Repository
@Primary
public class FirebaseFinanceRepository implements FinanceRepository {

    private final Firestore db;

    public FirebaseFinanceRepository(Firestore db) {
        this.db = db;
    }

    @Override
    public void save(String accountUid, FinanceType type, Finance finance) {
        Map<String, Object> financeData = new HashMap<>();
        financeData.put("balance", finance.getBalance());
        financeData.put("dailyWithdrawalLimit", finance.getDailyWithdrawalLimit());
        financeData.put("dailyPurchaseLimit", finance.getDailyPurchaseLimit());
        financeData.put("dailyTransferLimit", finance.getDailyTransferLimit());
        financeData.put("type", type.name());

        if (finance instanceof Credit) {
            Credit credit = (Credit) finance;
            financeData.put("creditLimit", credit.getCreditLimit());
            financeData.put("creditInterestRate", credit.getCreditInterestRate());
        } else if (finance instanceof Savings) {
            Savings savings = (Savings) finance;
            financeData.put("interestRate", savings.getInterestRate());
        }

        try {
            db.collection("accounts").document(accountUid).collection("finance_products")
                    .document(type.name()).set(financeData).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Failed to save finance product", e);
        }
    }

    @Override
    public Optional<Finance> find(String accountUid, FinanceType type) {
        try {
            DocumentSnapshot documentSnapshot = db.collection("accounts").document(accountUid)
                    .collection("finance_products").document(type.name()).get().get();

            if (documentSnapshot.exists()) {
                Map<String, Object> data = documentSnapshot.getData();
                String financeTypeStr = (String) data.get("type");
                FinanceType financeType = FinanceType.valueOf(financeTypeStr);

                int balance = ((Long) data.get("balance")).intValue();
                int dailyWithdrawalLimit = ((Long) data.get("dailyWithdrawalLimit")).intValue();
                int dailyPurchaseLimit = ((Long) data.get("dailyPurchaseLimit")).intValue();
                int dailyTransferLimit = ((Long) data.get("dailyTransferLimit")).intValue();

                switch (financeType) {
                    case CHEQUING:
                        return Optional.of(new Chequing(balance, dailyWithdrawalLimit, dailyPurchaseLimit, dailyTransferLimit));
                    case SAVINGS:
                        double interestRate = (Double) data.get("interestRate");
                        return Optional.of(new Savings(balance, dailyWithdrawalLimit, dailyPurchaseLimit, dailyTransferLimit, interestRate));
                    case CREDIT:
                        int creditLimit = ((Long) data.get("creditLimit")).intValue();
                        double creditInterestRate = (Double) data.get("creditInterestRate");
                        return Optional.of(new Credit(creditLimit, creditInterestRate, balance, dailyWithdrawalLimit, dailyPurchaseLimit, dailyTransferLimit, Clock.systemDefaultZone()));
                    default:
                        return Optional.empty();
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Failed to find finance product", e);
        }
        return Optional.empty();
    }

    @Override
    public boolean exists(String accountUid, FinanceType type) {
        return find(accountUid, type).isPresent();
    }

    @Override
    public int getNetPositionCentsForAccount(int accountUid) {
        return 0; // Not implemented
    }

    @Override
    public Map<String, Finance> loadForAccount(int accountId) {
        throw new UnsupportedOperationException("Implement Firebase mapping");
    }

    @Override
    public void save(int accountId, String key, Finance finance) {
        // This method signature is a bit ambiguous with the other save method.
        // For now, we delegate to the other save method.
        save(String.valueOf(accountId), FinanceType.valueOf(key), finance);
    }
}

