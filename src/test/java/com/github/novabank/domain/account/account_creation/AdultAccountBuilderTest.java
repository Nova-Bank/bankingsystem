package com.github.novabank.domain.account.account_creation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.github.novabank.domain.account.accounts.AdultAccount;
import com.github.novabank.domain.account.account_creation.AdultAccountBuilder;
import com.github.novabank.domain.finance.finance_accounts.Chequing;
import com.github.novabank.domain.finance.finance_creation.ChequingBuilder;
 



@DisplayName("Account abstract class Test")
class AdultAccountBuilderTest{
    private AdultAccountBuilder builder;
    private ChequingBuilder chequingBuilder =new ChequingBuilder();

         Chequing account = chequingBuilder
         .setBalance(1000)
         .setDailyPurchaseLimit(1000)
         .setDailyTransferLimit(1000)
         .setDailyWithdrawalLimit(1000)
         .build();

    static Stream<String> validEmails(){
        return Stream.of(
            "email@example.com",
            "email@example.com ",
            " email@example.com",
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
    "",
    " ",
    "em ail@example.com",
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
         builder =  new AdultAccountBuilder();
         
         builder
            .setEmail("example@gmail.com")
            .setPassword("123456790_")
            .setFullName("Josef Geshelin")
            .setDateOfBirth(LocalDate.of(2000, 3, 23))
            .setPhoneNumber("4432214353")
            .addFinanceProduct(account);
    }

    @AfterEach
    void cleanUp(){
        builder.reset();
    }

   @Test
    @DisplayName("Should Create a Adult Account with valid data stored")
    void validbuilder(){
         AdultAccount adult = builder
            .build();
        assertEquals("example@gmail.com", adult.getEmail());
        assertEquals("123456790_", adult.getPassword());
        assertEquals("Josef Geshelin", adult.getFullName());
        assertEquals(LocalDate.of(2000, 3, 23), adult.getDateOfBirth());
        assertEquals("4432214353", adult.getPhoneNumber());
        assertEquals("4432214353", adult.getPhoneNumber());
        assertEquals(account, adult.getFinanceProducts());
    }
    @Test
    @DisplayName("Ensure Reset works")
    void validReset(){
        builder
            .reset();
        assertEquals("AdultAccountbuilder[ email=null password=null fullName=null dateOfBirth=null phoneNumber=null]", builder.toString());
    }
    
    @ParameterizedTest(name = "Normalize: {0}")
    @MethodSource("validEmails")
    @DisplayName("Test wtih strange Valid Emails")
    void validEmail(String input){
        AdultAccount adult = builder
            .setEmail(input)
            .build();
            assertEquals(input, adult.getEmail());
    }
    @Test
    @DisplayName("Phone number should be cleaned up to only caontain the numbers")
    void normalizationPhoneNumber(){
         AdultAccount adult = builder
            .setPhoneNumber("(443) 221-4353")
            .build();
            assertEquals("4432214553", adult.getPhoneNumber());
    }

    @ParameterizedTest(name = "Normalize: {0}")
    @MethodSource("invalidEmails")
    @DisplayName("Test wtih invalid Valid Emails")
    void invalidEmail(String input){
        builder.setEmail(input);
        assertThrows(IllegalStateException.class, () -> builder.build());

    }

    @Test
    @DisplayName("Should raise error in DOB in the future")
    void futureDOB(){
        builder.setDateOfBirth(LocalDate.now().plusYears(1));
        assertThrows(IllegalStateException.class, () -> builder.build());
    }

    @ParameterizedTest(name = "Normalize: {0}")
    @ValueSource(strings = {
        "",
        " ",
        "short",
        "NoSpecialCharacter1",
        ""
    })
    @DisplayName("Should throw error at invaid Phone numbers")
    void invalidPhoneNumbers(String input){
        builder.setPhoneNumber(input);
        assertThrows(IllegalStateException.class, () -> builder.build());
    }

    @ParameterizedTest(name = "Normalize: {0}")
    @ValueSource(strings = {
        "Josef",
        "?",
        "شArabic",
        "Josef  Geshelin",
        ""
    })
    @DisplayName("Should throw error at invaid Names")
    void invalidNames(String input){
        builder.setFullName(input);
        assertThrows(IllegalStateException.class, () -> builder.build());
    }

    //Test invalid FinanceProducts with decorators

}
