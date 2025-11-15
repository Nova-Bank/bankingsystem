package com.github.novabank.builder;

import java.time.LocalDate;

import com.github.novabank.model.AdultAccount;;

/**
 * 
 * @author Josef Geshelin
 * @version 1.0
 * @since 2025-11-14
 */
public class AdultAccountBuilder implements AccountBuilder<AdultAccount> {

    private int UID;
    private String email;   
    private String password; 
    private String fullName;
    private LocalDate dateOfBirth;
    private String phoneNumber;

    public AdultAccountBuilder setUID(int UID){
        this.UID = UID;
        return this;
    }
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
        this.UID = 0;
        this.email = null;
         this.password = null;
         this.fullName = null;
         this.dateOfBirth  = null;
        this.phoneNumber  = null;
    }

    @Override
    public void validate(){

    }

    public String toString() {
    }
    
}
