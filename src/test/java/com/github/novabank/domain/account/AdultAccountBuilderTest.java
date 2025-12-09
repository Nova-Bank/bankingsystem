package com.github.novabank.domain.account;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.github.novabank.domain.account.AdultAccount;
import com.github.novabank.domain.account.AdultAccountBuilder;



@DisplayName("Account abstract class Test")
class AdultAccountBuilderTest{
    private AdultAccountBuilder Builder;
     String[] validEmails = {
    "email@example.com",
    "firstname.lastname@example.com",
    "email@subdomain.example.com",
    "firstname+lastname@example.com",
    "email@123.123.123.123",
    "email@[123.123.123.123]",
    "\"email\"@example.com",
    "1234567890@example.com",
    "email@example-one.com",
    "_______@example.com",
    "email@example.name",
    "email@example.museum",
    "email@example.co.jp",
    "firstname-lastname@example.com",

    "much.\"more\\ unusual\"@example.com",
    "very.unusual.\"@\".unusual.com@example.com",
    "very.\"(),:;<>[]\".VERY.\"very@\\\\\\ \"very\".unusual@strange.example.com"
    };
    String[] invalidEmails = {
    "plainaddress",
    "#@%^%#$@#$@#.com",
    "@example.com",
    "Joe Smith <email@example.com>",
    "email.example.com",
    "email@example@example.com",
    ".email@example.com",
    "email.@example.com",
    "email..email@example.com",
    "あいうえお@example.com",
    "email@example.com (Joe Smith)",
    "email@example",
    "email@-example.com",
    "email@111.222.333.44444",
    "email@example..com",
    "Abc..123@example.com",

    "\"(),:;<>[\\]@example.com",
    "just\"not\"right@example.com",
    "this\\ is\\\"really\\\"not\\\\allowed@example.com"
    };

    @BeforeEach
    void setUp(){
         Builder =  new AdultAccountBuilder();
    }

    @AfterEach
    void cleanUp(){
        Builder.reset();
    }

   @Test
    @DisplayName("Should Create a Adult Account with valid data stored")
    void Builder(){
         AdultAccount adult = Builder
            .setEmail("example@gmail.com")
            .setPassword("123456790_")
            .setFullName("Josef Geshelin")
            .setDateOfBirth(LocalDate.of(2000, 3, 23))
            .setPhoneNumber("4432214353")
            .build();
        assertEquals("example@gmail.com", adult.getEmail());
        assertEquals("123456790_", adult.getPassword());
        assertEquals("Josef Geshelin", adult.getFullName());
        assertEquals(LocalDate.of(2000, 3, 23), adult.getDateOfBirth());
        assertEquals("4432214353", adult.getPhoneNumber());
    }
    @Test
    @DisplayName("Ensure Reset works")
    void validReset(){
        Builder
            .setEmail("example@gmail.com")
            .setPassword("123456790_")
            .setFullName("Josef Geshelin")
            .setDateOfBirth(LocalDate.of(2000, 3, 23))
            .setPhoneNumber("4432214353")
            .build();
        Builder.reset();
        assertEquals("AdultAccountBuilder[ email=null password=null fullName=null dateOfBirth=null phoneNumber=null]", Builder.toString());

    }

    /* Cases
    1. Usual case
    2. Ensure reset works 
    Validate
    Valid
    1. validEmail
    2. Phone number removed in 
    Should raise error
    1. Invalid Emails
    2. Email, Password Spaces
    3. Password too short
    4. password no special characters
    5. Only First Name
    6. non roman character first
    7. more than one space between each word
    8. Date of Birth in the future
    9. Phone Number is letters
    10. 
    */

}
