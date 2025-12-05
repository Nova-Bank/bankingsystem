package com.github.novabank.model;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;



@DisplayName("Account abstract class Test")
class AccountTest{
    private AdultAccount validAdult;
    private ChildAccount validChild;
    
    @BeforeEach
    void setup(){
        validAdult = new AdultAccount("example@gmail.com", "123456790", "Josef Geshelin", LocalDate.of(2000, 3, 23), "4432214353");
        validChild = new ChildAccount("example@gmail.com", "123456790", "Toufic Fares", LocalDate.of(2010, 3, 23), "123 939 8723");

    }

    @Test
    @DisplayName("Should Create a Adult Account with valid data stored")
    void ValidAdultCreation(){
        assertNotNull(validAdult);
        assertEquals("example@gmail.com", validAdult.getEmail());
        assertEquals("123456790", validAdult.getPassword());
        assertEquals("Josef Geshelin", validAdult.getFullName());
        assertEquals(LocalDate.of(2000, 3, 23), validAdult.getDateOfBirth());
        assertEquals("4432214353", validAdult.getPhoneNumber());
    }

    @Test
    @DisplayName("Should Create a Child Account with valid data stored")
    void ValidChildCreation(){
        assertNotNull(validChild);
        assertEquals("example@gmail.com", validChild.getEmail());
        assertEquals("123456790", validChild.getPassword());
        assertEquals("Toufic Fares", validChild.getFullName());
        assertEquals(LocalDate.of(2010, 3, 23), validChild.getDateOfBirth());
        assertEquals("123 939 8723", validChild.getPhoneNumber());
    }

    @Test
    @DisplayName("Should Create a Child Account with valid data stored")
    void ValiAddChild(){
        validAdult.addChild(validChild);
        
    }


    
    /*
    Cases:
    2. addChild, removeChild Usual on both chld & Adult
    3. addChild, removeChild if child does not exist
    */
   
   /* Cases
    1. UID produces random UIDs
    */


}