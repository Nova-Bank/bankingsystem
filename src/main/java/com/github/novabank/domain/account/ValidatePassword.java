package com.github.novabank.domain.account;

import java.util.ArrayList;
import java.util.List;

public class ValidatePassword implements Validator<String> {

     /**
     * Validates password if the password (String) is at least 8 characters long, doesn't have any whitespace, and at least one special character 
     * @return true is password is valid 
     */    
    @Override
    public boolean validate(String pass) {

        if (pass == null) {
            return false;
        }
        
        if (pass.length() < 8) {
            return false;
        }
        
        if (pass.contains(" ") || pass.matches(".*\\s.*")) {
            return false;
        }
        
        if (!pass.matches(".*[!@#$%^&*()_+\\-=\\[\\]{}|;:',.<>?/~`].*")) {
            return false;
        }
        
        return true;

    }
}
