package main.model;

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

    private AdultAccount(AccountInfo info) {
        super(info);
    }

    /**
     * Factory method to create an AdultAccount with validation.
     * 
     * @param info Account information to validate
     * @return New AdultAccount instance
     * @throws InvalidAccountException if validation fails or age < 18
     */
    public static AdultAccount create(AccountInfo info) throws IllegalArgumentException {
        AccountInfoValidator validator = new AccountInfoValidator();
        
        if (!validator.validate(info)) {
            throw new IllegalArgumentException("Invalid account information");
        }
        
        int age = AccountInfoValidator.getAge(info.getDateOfBirth());
        if (age < MIN_AGE) {
            throw new IllegalArgumentException("Must be at least 18 years old for adult account");
        }
        
        return new AdultAccount(info);
    }

    /**
     * Link a child account to this adult account.
     * 
     * @param child Child account to add
     * @throws InvalidAccountException if max children reached
     */
    public void addChild(ChildAccount child) throws IllegalArgumentException {
        if (children.size() >= MAX_CHILDREN) {
            throw new IllegalArgumentException("Cannot add child. Maximum of 4 children allowed.");
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
}