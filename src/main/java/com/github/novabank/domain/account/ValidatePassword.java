package com.github.novabank.domain.account;

import java.util.ArrayList;
import java.util.List;

public class ValidatePassword implements Validator<String> {

     /**
     * Validates password if the password (String) is at least 8 characters long, doesn't have any whitespace, and at least one special character 
     * @return true is password is valid 
     */    
    @Override
    public ValidationResult validate(String pass) {

        List<String> errors = new ArrayList<>();

        if (pass == null) {
            errors.add("Password cannot be empty or null.");
        }
        
        if (pass.length() < 8) {
            errors.add("Password doesn't meet minimum length (at least 8 characters).");
        }
        
        if (pass.contains(" ") || pass.matches(".*\\s.*")) {
            errors.add("Password must NOT have any whitespace.");
        }
        
        if (!pass.matches(".*[!@#$%^&*()_+\\-=\\[\\]{}|;:',.<>?/~`].*")) {
            errors.add("Password must have at least one special character.");
        }
        
        return errors.isEmpty() ? ValidationResult.success() : ValidationResult.failure(errors);

    }
}
