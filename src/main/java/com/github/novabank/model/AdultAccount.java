package main.java.com.github.novabank.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Adult account - must be 18 or older.
 * Can have up to 4 child accounts linked.
 */
public class AdultAccount extends Account {
    
    private static final int MAX_CHILDREN = 4;
    private static final int MIN_AGE = 18;
    
    private final List<ChildAccount> children = new ArrayList<>();

    private AdultAccount(String email, String password, String fullName, 
                        LocalDate dateOfBirth, String phoneNumber) {
        super(email, password, fullName, dateOfBirth, phoneNumber);
    }

    /**
     * Factory method to create an AdultAccount with validation.
     * This is where the validation happens - in the implementation layer.
     * 
     * @param info Account information to validate
     * @return New AdultAccount instance
     * @throws IllegalArgumentException if validation fails or age < 18
     */
    public static AdultAccount create(AccountInfo info) throws IllegalArgumentException {
        AccountInfoValidator validator = new AccountInfoValidator();
        
        ValidationResult result = validator.validate(info);
        if (!result.isValid()) {
            throw new IllegalArgumentException("Invalid account information: " + result.getErrorMessage());
        }
        
        int age = AccountInfoValidator.getAge(info.getDateOfBirth());
        if (age < MIN_AGE) {
            throw new IllegalArgumentException("Must be at least 18 years old for adult account (current age: " + age + ")");
        }
        
        return new AdultAccount(
            info.getEmail(),
            info.getPassword(),
            info.getFullName(),
            info.getDateOfBirth(),
            info.getPhoneNumber()
        );
    }

    /**
     * Link a child account to this adult account.
     * 
     * @param child Child account to add
     * @throws IllegalArgumentException if max children reached
     */
    public void addChild(ChildAccount child) throws IllegalArgumentException {
        if (children.size() >= MAX_CHILDREN) {
            throw new IllegalArgumentException("Cannot add child. Maximum of " + MAX_CHILDREN + " children allowed.");
        }
        
        children.add(child);
        child.setParent(this); 
    }

    /**
     * Remove a child account link.
     * 
     * @param child Child account to remove
     * @return true if child was found and removed
     */
    public boolean removeChild(ChildAccount child) {
        if (children.remove(child)) {
            child.setParent(null);
            return true;
        }
        return false;
    }

    /**
     * Get number of linked children.
     */
    public int getChildCount() {
        return children.size();
    }
    
    /**
     * Get list of child accounts (unmodifiable).
     */
    public List<ChildAccount> getChildren() {
        return new ArrayList<>(children);
    }
}