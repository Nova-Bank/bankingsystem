package com.github.novabank.domain.account;

import java.time.LocalDate;;

/**
 * 
 * @author Josef Geshelin
 * @version 1.0
 * @since 2025-11-14
 */
public class ChildAccountBuilder implements AccountBuilder<ChildAccount> {

    private String email;   
    private String password; 
    private String fullName;
    private LocalDate dateOfBirth;
    private String phoneNumber;

    
    public ChildAccountBuilder setemail(String email){
        this.email = email;
        return this;
    }
    public ChildAccountBuilder setpassword(String password){
        this.password = password;
        return this;
    }
    public ChildAccountBuilder setfullName(String fullName){
        this.fullName = fullName;
        return this;
        
    }
    public ChildAccountBuilder setdateOfBirth(LocalDate DOB){
        this.dateOfBirth = DOB;
        return this;
    }
    public ChildAccountBuilder setphoneNumber(String phoneNumber){
        this.phoneNumber =phoneNumber;
        return this;
    }


    
    @Override
    public ChildAccount build(){
        validate();
        return new ChildAccount( email, password, fullName, dateOfBirth, phoneNumber);
    }

    @Override
    public void reset(){
        this.email = null;
         this.password = null;
         this.fullName = null;
         this.dateOfBirth  = null;
        this.phoneNumber  = null;
    }

    @Override

    // TODO validate for edge cases
    public void validate(){
        
        if (email == null){
          throw new IllegalStateException("email is required");
        }
        if (password == null){
          throw new IllegalStateException("password is required");
        }
        if (fullName == null){
          throw new IllegalStateException("fullName is required");
        }
        if (dateOfBirth == null){
          throw new IllegalStateException("dateOfBirth is required");
        }
        if (phoneNumber == null){
          throw new IllegalStateException("phoneNumber is required");
        }

    }

    @Override
    public String toString() {
        return String.format("ChildAccountBuilder[email=%s password=%s fullName=%s dateOfBirth=%s phoneNumber=%s]",
        email, password, fullName, dateOfBirth, phoneNumber);
    }
}
