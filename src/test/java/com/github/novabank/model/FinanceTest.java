package com.github.novabank.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;



@DisplayName("AdultAccount.java Test")
class FinanceTest{
    private AdultAccount validAdult;
    private ChildAccount validChild;
    
    @BeforeEach
    void setup(){
        validAdult = new AdultAccount("example@gmail.com", null, null, null, null);
    }
    /*
    Cases:
    1. AdultAccount Constructor works
    2. addChild, removeChild Usual on both chld & Adult
    3. addChild, removeChild if child does not exist
    */
   /* Child Account Cases
    1. Constructor works
    2.  
    */
   /* Cases
    1. UID produces random UIDs
    */


}