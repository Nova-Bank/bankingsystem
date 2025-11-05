# Bank System Architecture Roadmap

## 1. Clean Object-Oriented Shape

**Goal:** Build a modular, scalable, and maintainable system that separates concerns.

### Structure
- **Abstract `Account` class:** Base for all account types, containing shared fields and behaviors.
- **Interfaces:**
  - `InterestBearing`: Defines methods for calculating and applying interest.
  - `FeeCharging`: Defines methods for computing fees or transaction charges.
- **Concrete Implementations:**
  - `SavingsAccount`, `ChequingAccount`, and `CreditAccount` implement the appropriate interfaces.
- **Factory Pattern:**
  - `AccountFactory` validates input, assigns unique IDs (UUID), and returns properly configured account objects.
- **Service Layer:**
  - `BankService` handles all business operations — open, close, transfer, deposit, withdraw.
  - UI or API layers **must never** interact with entities directly; they go through the service.

---

## 2. Money & Validation

**Goal:** Prevent precision errors and enforce financial integrity.

- Use **`BigDecimal`** instead of `double` for all money values to ensure exact precision.
- Create a **`Currency` enum** for multi-currency support (CAD, USD, EUR, etc.).
- **Immutable IDs:** Use `UUID` for account and user identifiers.
- **Owner as Value Object:** Represent a customer with fields like `Name`, `Email`, and `Address`.
- **Input Validation Guards:**
  - Prevent negative deposits or withdrawals.
  - Enforce maximum withdrawal limits and daily transfer caps.
  - Ensure account state allows the requested operation.

---

## 3. Meaningful Errors

**Goal:** Replace vague exceptions with domain-specific ones for clarity.

- Custom exceptions:
  - `InsufficientFundsException`
  - `AccountNotFoundException`
  - `DuplicateAccountException`
  - `InvalidAmountException`
  - `OverdraftNotAllowedException`

These make debugging and testing business logic more predictable.

---

## 4. Persistence Layer

**Goal:** Make the system durable and data-driven.

- Define an `AccountRepository` interface for CRUD operations.
- Provide multiple implementations:
  - **In-memory** for testing.
  - **File/JSON-based** for lightweight persistence.
  - **JDBC/SQLite-based** for real database integration.
- **Transactions:**
  - Add a simple abstraction (`TransactionContext`) for begin/commit/rollback.
  - Ensure transfers are atomic — either both balances update or none do.

---

## 5. Concurrency & Thread Safety

**Goal:** Ensure correct behavior under concurrent access.

- Synchronize service-level methods or lock accounts individually.
- Use **fine-grained locks per account** instead of global locks for performance.
- Always **acquire locks in account ID order** to prevent deadlocks.
- Implement **idempotency keys** to prevent duplicate charges or transfers during retries or API replays.

---

## 6. Security Foundations

**Goal:** Protect user data and restrict unauthorized operations.

- **Authentication:**
  - Implement salted password hashing using **PBKDF2** or **BCrypt**.
  - Never store plaintext passwords.
- **Authorization:**
  - Role-based access (`USER`, `TELLER`, `ADMIN`) with method-level permission checks.
- **Audit Logging:**
  - Maintain an append-only log for every operation: who, what, when, where.

---

## 7. Business Rules & Logic

**Goal:** Reflect real-world financial constraints and rules.

- **Interest Strategies:** Different algorithms per account type.
- **Fee Strategies:** Define configurable fees per operation.
- **Account States:** (`ACTIVE`, `FROZEN`, `CLOSED`) — disallow operations when not `ACTIVE`.
- **Limits:**
  - Daily transfer caps.
  - KYC/AML compliance flags.
  - Holds for large deposits.

---

## 8. Design Patterns That Add Value

**Goal:** Use proven software patterns for clean, extensible design.

- **Strategy:** For interest and fee calculation logic.
- **Template Method:** Monthly closing or interest processing.
- **Observer:** Notify listeners (e.g., alerts on large transactions).
- **Command:** Represent transactions that support undo/redo.
- **Builder:** Simplify account creation with complex constructors.
- **Chain of Responsibility:** Multi-step approvals for large transfers.

---

## 9. Testing Strategy

**Goal:** Ensure correctness and stability through automated testing.

- **Unit Tests:** For service and repository layers using JUnit.
- **Parameterized Tests:** To verify logic under varying amounts or limits.
- **Property-Based Tests:** Guarantee invariants like “total bank balance remains constant after transfers.”
- **Concurrency Tests:** Parallel transfers must always preserve overall sums.

---

## 10. User Interface Layer

**Goal:** Keep user interaction isolated and minimal.

- Start with a **CLI (command-line interface)** for testing.
- Evolve to a **REST API** using JSON DTOs (Data Transfer Objects).
- Validate all input on the boundary — **never trust client input**.

---

## 11. Diagnostics & Monitoring

**Goal:** Make system behavior observable.

- **Structured Logging:** Record operation type, account ID, amount, result, timestamp.
- **Metrics & Monitoring:**
  - Track transaction count, error rates, latency (e.g., p95).

---

## 12. Packaging & Architecture Discipline

**Goal:** Enforce clean dependencies and maintainability.

### Recommended Packages
- `domain` — core entities, value objects, enums.
- `service` — use cases and business logic.
- `repo` — data access and persistence.
- `security` — authentication and authorization.
- `api` — user interface or REST endpoints.
- `util` — helper utilities and shared components.

Follow the **Dependency Inversion Principle**:
- Services depend on repository **interfaces**, not concrete implementations.

---

## ✅ Focus Areas for First Iteration

1. **Use `BigDecimal` for money.**
2. **Implement the Repository pattern.**
3. **Create a thread-safe Service layer with locking and idempotency keys.**

Once those are stable, expand into persistence, security, and advanced business logic.

