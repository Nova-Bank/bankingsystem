package com.github.novabank.domain.account;

import java.time.LocalDate;


/**
 * 
 * @author Josef Geshelin
 * @version 1.0
 * @since 2025-11-14
 */
public class AdultAccountBuilder implements AccountBuilder<AdultAccount> {

    private String email;   
    private String password; 
    private String fullName;
    private LocalDate dateOfBirth;
    private String phoneNumber;

    
    public AdultAccountBuilder setemail(String email){
        this.email = email;
        return this;
    }
    public AdultAccountBuilder setpassword(String password){
        this.password = password;
        return this;
    }
    public AdultAccountBuilder setfullName(String fullName){
        this.fullName = fullName;
        return this;
        
    }
    public AdultAccountBuilder setdateOfBirth(LocalDate DOB){
        this.dateOfBirth = DOB;
        return this;
    }
    public AdultAccountBuilder setphoneNumber(String phoneNumber){
        this.phoneNumber =phoneNumber;
        return this;
    }

    @Override
    public AdultAccount build(){
        validate();
        return new AdultAccount( email,  password,  fullName, 
                         dateOfBirth,  phoneNumber);
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
        return String.format("AdultAccountBuilder[ email=%s password=%s fullName=%s dateOfBirth=%s phoneNumber=%s]",
        email, password, fullName, dateOfBirth, phoneNumber);
    }
}
