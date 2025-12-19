package com.github.novabank.application;

import com.github.novabank.domain.account.AccountRepository;
import com.github.novabank.domain.account.accounts.Account;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Repository
public class FirebaseAccountRepository implements AccountRepository {

    @Override
    public Account findByUsername(String username) {
        // TODO map Firebase snapshot → Account
        throw new UnsupportedOperationException("Implement Firebase mapping");
    }

    @Override
    public Account findById(int uid) {
        throw new UnsupportedOperationException("Implement Firebase mapping");
    }

    @Override
    public void create(Account account) {

    }

    @Override
    public Map<String, Object> read(Account account) throws IOException, InterruptedException, ExecutionException {
        return Map.of();
    }

    @Override
    public void update(Account account, String field, Object value) throws IOException, InterruptedException, ExecutionException {

    }

    @Override
    public void delete(Account account) throws InterruptedException, ExecutionException, IOException {

    }

    @Override
    public Account findByEmail(String email) throws IOException, ExecutionException, InterruptedException {
        return null;
    }

    @Override
    public Account findByPhoneNumber(String phoneNumber) throws IOException, ExecutionException, InterruptedException {
        return null;
    }

    @Override
    public List<Account> findAll() {
        return List.of();
    }

    @Override
    public List<Account> loadAll() {
        throw new UnsupportedOperationException("Implement Firebase mapping");
    }

    @Override
    public Account get(int accountId) {
        return null;
    }

    @Override
    public void save(Account account) {
        // FirebaseDatabase.getInstance().getReference(...)
    }
}
