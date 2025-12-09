package com.github.novabank.domain.account;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/* NOT FORMAL DOCUMENTATION, Delete after file is Complete
Account is the Plug/Contract between the Domain layer & the infrastructure (Database)

 * Responsibilities:
 * - Define how the domain expects Accounts to be persisted.
 * - Expose only business-focused operations (not database details).
 * - Do NOT contain any SQL, JPA, or persistence logic.
 * - Enforces CRUD
 *
 * Implementations of this interface belong in the INFRASTRUCTURE layer and handle
 * actual storage (DB, file, API, etc.), including mapping between domain objects and persistence models.

 */
public interface AccountRepository<T>{

    void create(Account account);
    
    Map<String, Object> read(Account account) throws IOException, InterruptedException, ExecutionException;    

    void update(Account account, String field, Object value) throws IOException, InterruptedException, ExecutionException;

    void delete(Account account) throws InterruptedException, ExecutionException, IOException;
}
