package main.model;

import main.model.AccountInfo;
import java.time.LocalDate;
import java.time.Period;

/**
 * Validates AccountInfo before creating an Account.
 * Returns true if all fields are valid, false otherwise.
 */
public class AccountInfoValidator implements Validator<AccountInfo> {

    @Override
    public boolean validate(AccountInfo info) {
        if (info.getEmail() == null || info.getEmail().trim().isEmpty()) {
            return false;
        }
        
        if (info.getPassword() == null || info.getPassword().isEmpty()) {
            return false;
        }
        
        if (info.getFullName() == null || info.getFullName().trim().isEmpty()) {
            return false;
        }
        
        if (info.getDateOfBirth() == null) {
            return false;
        }
        
        if (info.getPhoneNumber() == null || info.getPhoneNumber().trim().isEmpty()) {
            return false;
        }
        
        return true;
    }

    public static int getAge(LocalDate dateOfBirth) {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }
}