package com.github.novabank.domain.account;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;



@DisplayName("Account abstract class Test")
class AdultAccountBuilderTest{
    private AdultAccountBuilder Builder;

    static Stream<String> validEmails(){
        return Stream.of(
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

        );
    }

    static Stream<String> invalidEmails(){
        return Stream.of(
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
        );
    }

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
    void validBuilder(){
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
            .reset();
        assertEquals("AdultAccountBuilder[ email=null password=null fullName=null dateOfBirth=null phoneNumber=null]", Builder.toString());
    }
    
    @ParameterizedTest(name = "Normalize: {0}")
    @MethodSource("validEmails")
    @DisplayName("Test wtih strange Valid Emails")
    void validEmail(String input){
        AdultAccount adult = Builder
            .setEmail(input)
            .setPassword("123456790_")
            .setFullName("Josef Geshelin")
            .setDateOfBirth(LocalDate.of(2000, 3, 23))
            .setPhoneNumber("4432214353")
            .build();
            assertEquals(input, adult.getEmail());
    }
    @Test
    @DisplayName("Phone number should be cleaned up to only caontain the numbers")
    void normalizationPhoneNumber(){

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
