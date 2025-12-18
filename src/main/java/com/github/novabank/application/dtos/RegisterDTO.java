package com.github.novabank.application.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * RegisterDTO is a Data Transfer Object used to receive
 * user registration information from the client.
 *
 * This DTO is typically used when a new user creates
 * an account and provides personal and account details.
 */
public class RegisterDTO {

    /**
     * The username chosen by the user.
     * <p>
     * This field is required and cannot be blank.
     */
    @NotBlank
    private String username;

    /**
     * The email address provided by the user.
     * <p>
     * This field is required and cannot be blank.
     */
    @NotBlank
    private String email;

    /**
     * The password chosen by the user.
     * <p>
     * This field is required and cannot be blank.
     */
    @NotBlank
    private String password;

    /**
     * The age of the user.
     * <p>
     * This field must not be null and must be at least 1.
     */
    @NotNull
    @Min(1)
    private Integer age;

    /**
     * The type of bank account being created.
     * <p>
     * Expected values are "Adult" or "Youth".
     */
    @NotBlank
    private String accountType; // Adult / Youth

    /**
     * Returns the username.
     *
     * @return the username provided by the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     *
     * @param username the username provided by the user
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the email address.
     *
     * @return the email provided by the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address.
     *
     * @param email the email provided by the user
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the password.
     *
     * @return the password provided by the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     *
     * @param password the password provided by the user
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the user's age.
     *
     * @return the age of the user
     */
    public Integer getAge() {
        return age;
    }

    /**
     * Sets the user's age.
     *
     * @param age the age of the user
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * Returns the account type.
     *
     * @return the account type ("Adult" or "Youth")
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     * Sets the account type.
     *
     * @param accountType the account type ("Adult" or "Youth")
     */
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}
