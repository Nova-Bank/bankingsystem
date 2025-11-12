package main.java.com.github.novabank.model;

import java.time.LocalDate;

/**
 * Child account - must be under 18.
 * Must be linked to an adult account.
 */
public class ChildAccount extends Account {

    private static final int MAX_AGE = 17;
    
    private AdultAccount parent;

    private ChildAccount(String email, String password, String fullName, LocalDate dateOfBirth, String phoneNumber) {
        super(email, password, fullName, dateOfBirth, phoneNumber);
    }

    /**
     * Factory method to create a ChildAccount with validation.
     * This is where the validation happens - in the implementation layer.
     * 
     * @param info Account information to validate
     * @param parent Parent adult account
     * @return New ChildAccount instance
     * @throws IllegalArgumentException if validation fails or age >= 18
     */
    public static ChildAccount create(AccountInfo info, AdultAccount parent) throws IllegalArgumentException {
        
        if (parent == null) {
            throw new IllegalArgumentException("Child account must have a parent");
        }
        
        AccountInfoValidator validator = new AccountInfoValidator();
        ValidationResult result = validator.validate(info);
        if (!result.isValid()) {
            throw new IllegalArgumentException("Invalid account information: " + result.getErrorMessage());
        }
        
        int age = AccountInfoValidator.getAge(info.getDateOfBirth());
        if (age > MAX_AGE) {
            throw new IllegalArgumentException("Must be 17 or younger for child account (current age: " + age + ")");
        }
        
        ChildAccount child = new ChildAccount(
            info.getEmail(),
            info.getPassword(),
            info.getFullName(),
            info.getDateOfBirth(),
            info.getPhoneNumber()
        );
        
        parent.addChild(child);
        
        return child;
    }

    /**
     * Set parent account (package-private - only AdultAccount can call this).
     */
    void setParent(AdultAccount parent) {
        this.parent = parent;
    }

    /**
     * Get parent account.
     */
    public AdultAccount getParent() {
        return parent;
    }

    /**
     * Check if child has a parent linked.
     */
    public boolean hasParent() {
        return parent != null;
    }
}