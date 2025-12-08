
package com.github.novabank.domain.account;

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

    public AdultAccount(String email, String password, String fullName, 
                        LocalDate dateOfBirth, String phoneNumber) {
        super(email, password, fullName, dateOfBirth, phoneNumber);
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