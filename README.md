# Banking System

## Object Oriented Design and Architecture

🔗 **Repository:** https://github.com/Nova-Bank

## Authors
- Toufic Fares 
- Josef Geshelin 
- Evan Wee
- Oluwatobi Kasumu 
- Harmann Singh

---
 
## 1. Introduction

This project presents the design and implementation of a **Banking System** built to demonstrate strong object oriented programming practices, layered architectural design, and the use of classical design patterns within a realistic financial domain.

Rather than focusing solely on functional correctness, the primary objective of this project is **architectural quality**. The system emphasizes separation of concerns, extensibility, maintainability, and policy driven behavior. Banking was selected as the domain because it naturally requires strict rule enforcement, state management, and clear ownership of responsibilities, making it an ideal context for applying advanced software engineering principles.

This project serves as a practical synthesis of concepts such as abstraction, polymorphism, SOLID principles, and pattern driven design.

---

## 2. System Overview

The Banking System models customers, accounts, financial products, and transactions in a controlled environment. It supports:

- Secure user authentication
- Account lifecycle management
- Transaction processing
- Enforcement of financial policies such as overdraft limits, interest calculations, and transaction caps

The system is intentionally designed to be **modular and extensible**. New account types, financial products, or persistence mechanisms can be introduced with minimal changes to existing code. This mirrors real world banking systems, where business rules evolve over time without requiring large scale rewrites of core logic.

---

## 3. Key Features

The system provides the following core functionality:

- User authentication including registration, login, logout, and account recovery
- Support for multiple account types with age based restrictions
- Financial products including Chequing, Savings, and Credit accounts
- Transaction management including withdrawals, payments, statements, and transfers
- Policy enforcement covering overdrafts, credit limits, interest rates, and daily transaction caps

Each feature is implemented with an emphasis on correctness, clarity, and long term maintainability.

---

## 4. Architectural Design

### 4.1 Layered Architecture

The system follows a layered architectural model to enforce separation of concerns.

### Presentation / API Layer
- Defines the system’s public entry points
- Handles user input
- Exposes account related operations
- Translates external requests into application level actions
- Contains **no business logic**

### Application / Service Layer
- Coordinates business workflows
- Enforces system wide rules
- Validates requests
- Manages account lifecycles
- Orchestrates interactions between domain objects
- Acts as the boundary between external input and core business logic

### Domain / Model Layer
- Represents core business concepts
- Contains the account hierarchy, financial products, transaction logic, and policy enforcement rules
- Completely independent of storage and presentation concerns

### Persistence / Storage Layer
- Provides centralized, in memory storage for registered accounts and financial data
- Abstracted so a database backed implementation can replace it in the future without impacting domain logic

This structure enforces clean dependencies and aligns with professional software architecture practices.

---

## 5. Domain Model Design

### 5.1 Account Abstraction

The abstract **Account** class defines shared identity and behavior for all account types.

**Common attributes include:**
- Unique identifier
- Personal information
- Authentication credentials
- Contact details

**Core operations include:**
- Login
- Logout
- Account recovery
- Registration

These operations ensure consistent behavior across all account types.

**Concrete implementations:**
- Youth Account
- Adult Account

These subclasses enforce age based eligibility rules while inheriting shared authentication and lifecycle behavior.

---

### 5.2 Financial Product Abstraction

The **Finance** class encapsulates shared financial behavior across all financial products. It centralizes logic related to:

- Interest calculations
- Transaction limits
- Account status
- Policy enforcement

**Concrete implementations:**
- Chequing
- Savings
- Credit

Each product applies its own rules while leveraging shared behavior defined in the abstraction. This design prevents duplication and ensures consistent enforcement of financial policies.

---

## 6. Object Creation and Lifecycle Management

### 6.1 Factory Pattern
- Centralizes creation of accounts and financial products
- Determines which account type to create based on eligibility criteria
- Decouples object creation from business logic
- Improves maintainability and extensibility

### 6.2 Builder Pattern
- Constructs complex account and finance objects incrementally
- Eliminates long constructors
- Improves readability
- Allows safe configuration of optional attributes
- Enforces validation rules during construction

This ensures that partially constructed or invalid objects cannot exist in the system.

---

## 7. Core Services

The system includes several focused services, each adhering to the **Single Responsibility Principle**:

- **Account Service**
    - Manages registration, authentication, and lifecycle operations

- **Account Builder**
    - Coordinates controlled account creation

- **Account Storage**
    - Provides centralized data access

- **Statements Service**
    - Generates transaction histories

- **Transfer Service**
    - Enables peer to peer transfers

Each service is cohesive, testable, and loosely coupled from other components.

---

## 8. Design Pattern Integration

### Creational Patterns
- **Factory** centralizes object creation
- **Builder** manages complex object construction

---

## 9. Security and Policy Enforcement

Financial policies are enforced at the **domain level**, not the interface level. This ensures that all operations, regardless of entry point, are subject to the same validation rules and constraints.

---

## 10. Web Interface and Backend Integration

A web based interface implemented with **HTML, CSS, and JavaScript** serves as the presentation layer. It provides:

- Login
- Registration
- Dashboards
- Transaction views

The front end communicates with the Java backend via HTTP requests using **JSON based DTOs**. Business logic and policy enforcement remain exclusively in the backend, ensuring security and consistency. This separation reflects modern web application architecture.

---

## 11. Build and Execution

- Requires **JDK 17 or later**
- Built and executed using **Maven**
- Operates using **in memory storage** by default
- Designed to support future persistence extensions

---

## 12. Reflection

This project demonstrates applied understanding of object oriented design, architectural layering, and design pattern integration. Multiple patterns coexist to solve complex, real world problems within a financial domain.

The system balances correctness, extensibility, and clarity. The architecture supports future growth while maintaining strong guarantees around data integrity and policy enforcement.

---

## 13. Conclusion

The Banking System demonstrates disciplined object oriented design, layered architecture, and thoughtful use of classical design patterns.

The system is modular, extensible, and maintainable, making it suitable for continued development and real world inspired use cases.
