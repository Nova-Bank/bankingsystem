package com.github.novabank.domain.account.accounts;

import com.github.novabank.domain.account.accounts.AdultAccount;
import com.github.novabank.domain.account.accounts.ChildAccount;
import com.github.novabank.domain.finance.Finance;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.commons.math3.exception.NullArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;



@DisplayName("Adult And Child Account class Test")
class AccountTest{
    private AdultAccount validAdult;
    private ChildAccount validChild;
    private ChildAccount nullChild;

    
    @BeforeEach
    void setup(){
        // validAdult = new AdultAccount("example@gmail.com", "123456790", "Josef Geshelin", LocalDate.of(2000, 3, 23), "4432214353");
        // validChild = new ChildAccount("example@gmail.com", "123456790", "Toufic Fares", LocalDate.of(2010, 3, 23), "123 939 8723");

        validAdult = new AdultAccount("example@gmail.com", "123456790", "Josef Geshelin", LocalDate.of(2000, 3, 23), "4432214353", new HashMap<String, Finance>());
        validChild = new ChildAccount("example@gmail.com", "123456790", "Toufic Fares", LocalDate.of(2010, 3, 23), "123 939 8723", new HashMap<String, Finance>());

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
        List<ChildAccount> expectedList = new ArrayList<>();
        expectedList.add(validChild);
        
        validAdult.addChild(validChild);
        assertEquals(1, validAdult.getChildCount());
        assertEquals(expectedList, validAdult.getChildren());
        assertEquals(validAdult, validChild.getParent());
    }
    @Test
    @DisplayName("Should Remove a Child Account with valid data stored")
    void ValidRemoveChild(){
        List<ChildAccount> expectedList = new ArrayList<>();
        
        validAdult.addChild(validChild);
        validAdult.removeChild(validChild);
        assertEquals(0, validAdult.getChildCount());
        assertEquals(expectedList, validAdult.getChildren());
        assertFalse(validChild.hasParent());
    }
    @Test
    @DisplayName("Should Remove a Child Account with valid data stored")
    void NullRemoveChild(){
        assertFalse(validAdult.removeChild(validChild));
    }
    @Test
    @DisplayName("Should Remove a Child Account with valid data stored")
    void NullAddChild(){
        assertThrows(IllegalArgumentException.class, () -> validAdult.addChild(nullChild));
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